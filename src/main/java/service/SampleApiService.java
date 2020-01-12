package service;

import service.dto.SampleItemDto;
import service.dto.TestResponseDto;

public class SampleApiService {

    public TestResponseDto sendRequest(SampleItemDto requestDto) {
        TestResponseDto responseDto = new TestResponseDto();
        responseDto.setItemNameCode(requestDto.getItemName() + "_OK");
        responseDto.setResultCode(1L);
        responseDto.setMessage("OK");

        return responseDto;
    }

}
