package com.example.groom.domain.auth.refreshToken;

import com.example.groom.common.exception.CustomException;
import com.example.groom.common.exception.ErrorCode;
import com.example.groom.entity.domain.auth.QRefreshToken;
import com.example.groom.entity.domain.auth.RefreshToken;
import com.example.groom.entity.domain.auth.UserInfo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static com.example.groom.entity.domain.auth.QRefreshToken.refreshToken1;

@Transactional
public class RefreshTokenRepositoryImpl implements RefreshTokenRepositoryCustom {



    @Autowired
    private EntityManager em;

    private final JPAQueryFactory query;

    public RefreshTokenRepositoryImpl(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }


    @Override
    public void assignRefreshToken(String refreshToken, UserInfo userInfo) {
        RefreshToken refreshToken2 = query.selectFrom(refreshToken1).where(refreshToken1.userInfo.eq(userInfo)).fetchOne();
        if(refreshToken2 != null) {
            QRefreshToken qRefreshToken = new QRefreshToken("d");
            query.delete(qRefreshToken).where(qRefreshToken.userInfo.eq(userInfo)).execute();
        }
        RefreshToken token = new RefreshToken(refreshToken, userInfo);
        em.persist(token);

    }

    @Override
    public RefreshToken getRefreshTokenFromDB(String refreshToken) {
        RefreshToken existsToken = query.selectFrom(refreshToken1)
                .where(refreshToken1.refreshToken.eq(refreshToken))
                .fetchOne();
        if (existsToken == null) throw new CustomException(ErrorCode.REFRESH_TOKEN_INVALID);
        return existsToken;
    }


}
