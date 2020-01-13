package service;

import entity.Sample;
import repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import service.converter.SampleConverter;
import service.dto.SampleItemDto;
import service.dto.TestRequestDto;
import service.dto.TestResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class SampleService {

    private static final long ERROR_CODE = -1;

    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private SampleApiService sampleApiService;

    @Autowired
    private SampleConverter sampleConverter;

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
