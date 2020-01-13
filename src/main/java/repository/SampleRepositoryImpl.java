package repository;

import entity.Sample;
import com.google.common.collect.Lists;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

@Repository
public class SampleRepositoryImpl extends QueryDslRepositorySupport {

	QSample sample = QSample.sample;

	public SampleRepositoryImpl() {
		super(SampleRepositoryImpl.class);
	}

	public Sample findByCodeForUpdate(Long code) {
		List<Predicate> predicates = Lists.newArrayList();
		predicates.add(sample.code.eq(code));

		return (Sample) getQuerydsl().createQuery(sample)
			.where(ExpressionUtils.allOf(predicates))
			.setLockMode(LockModeType.PESSIMISTIC_WRITE)
			.fetchOne();
	}

}
