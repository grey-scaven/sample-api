package com.grey.sample.service.provider;

import com.grey.sample.repository.SampleRepository;
import com.grey.sample.service.SampleDetails;
import com.grey.sample.service.datafetcher.AllSamplesDataFetcher;
import com.grey.sample.service.datafetcher.SampleDataFetcher;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SampleProvider implements SampleDetails {

    private final SampleRepository sampleRepository;

    private final AllSamplesDataFetcher allSamplesDataFetcher;

    private final SampleDataFetcher sampleDataFetcher;

    @Value("classpath:samples.graphql")
    Resource resource;

    private GraphQL graphQL;

    public SampleProvider(SampleRepository sampleRepository, AllSamplesDataFetcher allSamplesDataFetcher, SampleDataFetcher sampleDataFetcher) {
        this.sampleRepository = sampleRepository;
        this.allSamplesDataFetcher = allSamplesDataFetcher;
        this.sampleDataFetcher = sampleDataFetcher;
    }

    @Override
    public ExecutionResult execute(String query) {
        return graphQL.execute(query);
    }

}
