package Repository;

import Entity.Sample;

public interface SampleRepository {

    Sample findByCodeForUpdate(Long code);

}
