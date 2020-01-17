package com.grey.sample.service;

import graphql.ExecutionResult;
import org.springframework.stereotype.Service;

@Service
public class SampleUseCase {

    private final SampleDetails sampleDetails;

    public SampleUseCase(SampleDetails sampleDetails) {
        this.sampleDetails = sampleDetails;
    }

    public ExecutionResult execute(String query) {
        return sampleDetails.execute(query);
    }

}
