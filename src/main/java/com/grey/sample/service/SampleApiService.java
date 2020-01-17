package com.grey.sample.service;

import com.grey.sample.service.dto.SampleItemDto;
import com.grey.sample.service.dto.TestResponseDto;

public class SampleApiService {

    public TestResponseDto sendRequest(SampleItemDto requestDto) {
        TestResponseDto responseDto = new TestResponseDto();
        responseDto.setItemNameCode(requestDto.getItemName() + "_OK");
        responseDto.setResultCode(1L);
        responseDto.setMessage("OK");

        return responseDto;
    }

}
