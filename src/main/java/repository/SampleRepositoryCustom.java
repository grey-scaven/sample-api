package repository;

import entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepositoryCustom {

    Sample findByCodeForUpdate(Long code);

}
