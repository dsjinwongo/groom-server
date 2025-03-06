package com.example.groom.entity.domain;


import com.example.groom.entity.common.BaseEntity;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import static java.time.LocalDateTime.now;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class InviteCode extends BaseEntity {
    @Value("${inviteExpireMS}")
    private Long inviteExpireMS;

    @Column
    private String code;

    private String generateCode(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    protected InviteCode(){
        this.code = generateCode();
    }


    public boolean getExpired(){
        return now().isAfter(super.getUpdatedAt().plus(inviteExpireMS, ChronoUnit.MILLIS));
    }
}
