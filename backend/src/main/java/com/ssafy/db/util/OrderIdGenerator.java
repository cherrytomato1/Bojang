package com.ssafy.db.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

public class OrderIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

        String result = "S";

        // 현재 시스템 시간 구하기
        long systemTime = System.currentTimeMillis();

        // 출력 형태를 위한 formmater
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMddss", Locale.KOREA);

        // format에 맞게 출력하기 위한 문자열 변환
        String dTime = formatter.format(systemTime);

        result += dTime;

        Random rand = new Random();

        for(int i=0;i<4;i++) {
            result += String.valueOf(rand.nextInt(10));
        }

        return result;
    }
}
