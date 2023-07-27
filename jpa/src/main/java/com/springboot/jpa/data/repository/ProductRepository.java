package com.springboot.jpa.data.repository;

import com.springboot.jpa.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//Repository : 엔티티가 생성한 데이터베이스에 접근하는데 사용
//생성방법 : 접근하려는 테이블과 매핑되는 엔티티에대한 인터페이스 생성 후 JpaRepository 상속 받음
public interface ProductRepository extends JpaRepository<Product, Long> { //<대상 엔티티, 대상 엔티티의 @Id 필드 타입>

}