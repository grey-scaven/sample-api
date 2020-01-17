package com.grey.sample.repository;

import com.grey.sample.entity.Sample;

public interface SampleRepositoryCustom {

    Sample findByCodeForUpdate(Long code);

}
