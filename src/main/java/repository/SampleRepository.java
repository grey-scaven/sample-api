package repository;

import entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Long>, SampleRepositoryCustom {

    Sample findByCode(Long code);

}
