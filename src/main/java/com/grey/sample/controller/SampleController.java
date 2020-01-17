package com.grey.sample.controller;

import com.grey.sample.service.SampleUseCase;
import graphql.ExecutionResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/samples")
public class SampleController {

    private final SampleUseCase sampleUseCase;

    public SampleController(SampleUseCase sampleUseCase) {
        this.sampleUseCase = sampleUseCase;
    }

    @PostMapping
    public ResponseEntity<Object> getSampleByQuery(@RequestBody String query) {
        try {
            ExecutionResult execute = sampleUseCase.execute(query);

            return new ResponseEntity<>(execute, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
