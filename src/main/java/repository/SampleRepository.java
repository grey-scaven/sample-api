package repository;

import entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Long> {

    Sample findByCodeForUpdate(Long code);

}
