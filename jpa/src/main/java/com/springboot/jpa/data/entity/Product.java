package com.springboot.jpa.data.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
/*
엔티티
데이터베이스에 대응하는 클래스, 데이터베이스에 쓰일 테이블과 칼럼 정의
(1) 데이터베이스에 테이블을 생성하기 위해 직접 쿼리를 작성할 필요없음
(2)  엔티티에 어노테이션을 사용하면 테이블 간의 연관관계를 정의할 수 있음
*/

@Entity // 해당 클래스가 엔티티임을 명시하기위한 어노테이션, 테이블과 일대일 매칭
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "name")
@Table(name = "product") //클래스이름과 테이블이름을 다르게 지정해야하는 경우 사용
public class Product {

    @Id // 모든 엔티티에 @id 어노테이션 필요, 테이블의 기본값 역할
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 해당 필드값을 어떤 방식으로 자동생성할지 결정할 때 사용
    private Long number;

    @Column(nullable = false) //필드에 설정을 더할때 사용 name, nullable, length, unique 등을 요소로 사용
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}