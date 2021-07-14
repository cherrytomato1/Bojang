package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 모델 간 공통 사항 정의.
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mySql의 AUTO_INCREMENT를 사용(대리키)
    Long id = null;
}
