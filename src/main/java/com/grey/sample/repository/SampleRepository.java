package com.grey.sample.repository;

import com.grey.sample.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Long>, SampleRepositoryCustom {

    Sample findByCode(Long code);

}
