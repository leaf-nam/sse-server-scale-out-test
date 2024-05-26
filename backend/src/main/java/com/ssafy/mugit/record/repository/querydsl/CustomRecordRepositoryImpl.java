package com.ssafy.mugit.record.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.mugit.record.entity.Record;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import static com.ssafy.mugit.flow.main.entity.QFlow.flow;
import static com.ssafy.mugit.mugitory.entity.QMugitory.mugitory;
import static com.ssafy.mugit.record.entity.QRecord.record;
import static com.ssafy.mugit.record.entity.QRecordSource.recordSource;
import static com.ssafy.mugit.record.entity.QSource.source;
import static com.ssafy.mugit.user.entity.QProfile.profile;
import static com.ssafy.mugit.user.entity.QUser.user;

@Repository
public class CustomRecordRepositoryImpl implements CustomRecordRepository {
    private final JPAQueryFactory queryFactory;

    public CustomRecordRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Record findByIdWithUser(Long recordId) {
        return queryFactory.selectFrom(record)
                .leftJoin(record.flow, flow).fetchJoin()
                .leftJoin(flow.user, user).fetchJoin()
                .leftJoin(user.profile, profile).fetchJoin()
                .leftJoin(record.recordSources, recordSource).fetchJoin()
                .leftJoin(recordSource.source, source).fetchJoin()
                .leftJoin(record.mugitory, mugitory).fetchJoin()
                .where(record.id.eq(recordId))
                .fetchOne();
    }
}
