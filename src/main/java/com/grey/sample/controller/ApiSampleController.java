package com.grey.sample.controller;

import com.grey.sample.controller.dto.TestRequestIF;
import com.grey.sample.controller.dto.UpdateSampleRequestIF;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.grey.sample.service.SampleService;
import com.grey.sample.service.dto.TestRequestDto;

import javax.validation.Valid;

@RestController
public class ApiSampleController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SampleService sampleService;

    @RequestMapping(value = "/api/v1/test", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity test(@Valid @RequestBody TestRequestIF requestIF) {
        try {
            TestRequestDto requestDto = modelMapper.map(requestIF, TestRequestDto.class);
            sampleService.test(requestDto);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/v1/sample/update", method = RequestMethod.PUT)
    public ResponseEntity updateSample(@Valid @RequestBody UpdateSampleRequestIF requestIF) {
        try {
            // TODO: Implement feature
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
