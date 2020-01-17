package com.grey.sample.service.datafetcher;

import com.grey.sample.entity.Sample;
import com.grey.sample.repository.SampleRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SampleDataFetcher implements DataFetcher<Sample> {

    private final SampleRepository sampleRepository;

    public SampleDataFetcher(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Sample get(DataFetchingEnvironment dataFetchingEnvironment) {
        Long id = dataFetchingEnvironment.getArgument("id");

        return sampleRepository.findOne(id);
    }

}
