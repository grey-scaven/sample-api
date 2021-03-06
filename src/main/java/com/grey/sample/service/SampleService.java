package com.grey.sample.service;

import com.grey.sample.entity.Sample;
import com.grey.sample.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.grey.sample.service.converter.SampleConverter;
import com.grey.sample.service.dto.SampleItemDto;
import com.grey.sample.service.dto.TestRequestDto;
import com.grey.sample.service.dto.TestResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SampleService {

    private static final long ERROR_CODE = -1;

    private final SampleRepository sampleRepository;

    private final SampleApiService sampleApiService;

    private final SampleConverter sampleConverter;

    @Transactional
    public void test(TestRequestDto requestDto) {
        try {
            Optional<Sample> maybeSample = getSampleByCodeForUpdate(requestDto.getCode());
            maybeSample.ifPresent(sample -> {
                List<SampleItemDto> sampleItemDtoList = sample.getSampleItemList().stream()
                    .map(sampleConverter::convert).collect(Collectors.toList());

                callApi(sampleItemDtoList);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(readOnly = true)
    public Optional<Sample> getSampleByCodeForUpdate(Long code) {
        Sample sample = sampleRepository.findByCodeForUpdate(code);

        return Optional.ofNullable(sample);
    }

    private void callApi(List<SampleItemDto> sampleItemDtoList) {
        List<CompletableFuture<TestResponseDto>> completableFutureList =
            sampleItemDtoList.stream()
                .map(sampleItemDto -> CompletableFuture.supplyAsync(() -> sampleApiService.sendRequest(sampleItemDto))
                    .handle((result, throwable) -> {
                        if (throwable != null) {
                            TestResponseDto failedResult = new TestResponseDto();
                            failedResult.setResultCode(ERROR_CODE);
                            failedResult.setMessage(throwable.getMessage());
                            return failedResult;
                        }
                        return result;
                    }))
                .collect(Collectors.toList());

        List<TestResponseDto> resultList =
            completableFutureList.stream().map(CompletableFuture::join).collect(Collectors.toList());

        if (resultList.stream().anyMatch(result -> result.getResultCode().equals(ERROR_CODE))) {
            resultList.forEach(result -> {
                if (result.getResultCode() != ERROR_CODE) {
                    // TODO: Implement rollback logic (AWS SQS)
                }
            });
            Optional<TestResponseDto> callResult = resultList.stream()
                .filter(result -> result.getResultCode().equals(ERROR_CODE)).findAny();

            throw new RuntimeException(callResult.isPresent() ? callResult.get().getMessage() : "FAIL");
        }
    }
}
