package com.grey.sample.service.datafetcher;

import com.grey.sample.entity.Sample;
import com.grey.sample.repository.SampleRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllSamplesDataFetcher implements DataFetcher<List<Sample>> {

    private final SampleRepository sampleRepository;

    public AllSamplesDataFetcher(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    @Override
    public List<Sample> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return sampleRepository.findAll();
    }

}
