package com.spring.loginmodule.util;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 자바 엔티티 클래스들이 BAseTimeEntity 를 상속할 경우 필드(아래 두 변수)들도 칼럼으로 인식하게 한다.
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate // Entity 생성되고 저장될 때 시간 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate // 값 변경시 시간 자동 저장
    private LocalDateTime modifiedDate;

}
