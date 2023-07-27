package com.springboot.jpa.data.dao.impl;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

// 인터페이스 구현체 클래스
@Component
public class ProductDAOImpl implements ProductDAO {

    //데이터베이스에 접근하기 위해 repository interface를 사용해 의존성 주입--------------
    private ProductRepository productRepository;
    
    @Autowired
    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    //------------------------------------------------------------------------------

    @Override
    public Product insertProduct(Product product) { //product 엔티티를 데이터베이스에 저장하는 기능 수행
        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    // 예제 6.12
    @Override
    public Product selectProduct(Long number) { //product 엔티티를 데이터베이스에서 조회하는 메서드
        Product selectedProduct = productRepository.getById(number); //getById() : 단건 조회를 위한 기본 메서드

        return selectedProduct;
    }

    // 예제 6.15
    @Override 
    public Product updateProductName(Long number, String name) throws Exception { //product 데이터의 상품명을 업데이트하는 메서드, jpa는 값을 갱신할 때 update라는 키워드를 사용하지 않음
        Optional<Product> selectedProduct = productRepository.findById(number);

        Product updatedProduct;
        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();

            product.setName(name);  
            product.setUpdatedAt(LocalDateTime.now());

            updatedProduct = productRepository.save(product);
        } else {
            throw new Exception();
        }

        return updatedProduct;
    }

    // 예제 6.17
    @Override
    public void deleteProduct(Long number) throws Exception { //삭제 메서드 구현 
        Optional<Product> selectedProduct = productRepository.findById(number); // findById() 메서드를 통해 객체를 가져옴

        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();

            productRepository.delete(product); // delete() 메서드로 객체 삭제
        } else {
            throw new Exception();
        }
    }
}