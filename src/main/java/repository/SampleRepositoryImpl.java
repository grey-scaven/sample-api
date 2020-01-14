package repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import entity.QSample;
import entity.Sample;
import com.google.common.collect.Lists;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;

import javax.persistence.LockModeType;
import java.util.List;

@RequiredArgsConstructor
public class SampleRepositoryImpl implements SampleRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	QSample sample = QSample.sample;

	@Override
	public Sample findByCodeForUpdate(Long code) {
		List<Predicate> predicates = Lists.newArrayList();
		predicates.add(sample.code.eq(code));

		return (Sample) queryFactory.selectFrom(sample)
			.where(ExpressionUtils.allOf(predicates))
			.setLockMode(LockModeType.PESSIMISTIC_WRITE)
			.fetchOne();
	}

}
