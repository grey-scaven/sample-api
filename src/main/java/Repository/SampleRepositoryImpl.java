package Repository;

import Entity.Sample;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.function.Predicate;

public class SampleRepositoryImpl extends QueryDslRepositorySupport implements SampleRepository {

	QSample sample = QSample.sample;

	public SampleRepositoryImpl() {
		super(SampleRepositoryImpl.class);
	}

	@Override
	@PersistenceContext(unitName = "sample-api")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public Sample findByCodeForUpdate(Long code) {
		List<Predicate> predicates = Lists.newArrayList();
		predicates.add(sample.code.eq(code));

		return (Sample) getQuerydsl().createQuery(sample)
			.where(ExpressionUtils.allOf(predicates))
			.setLockMode(LockModeType.PESSIMISTIC_WRITE)
			.fetchOne();
	}

}
