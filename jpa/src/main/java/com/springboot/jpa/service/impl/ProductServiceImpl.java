package com.springboot.jpa.service.impl;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.dto.ProductDto;
import com.springboot.jpa.data.dto.ProductResponseDto;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.service.ProductService;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// 서비스 인터페이스 구현체 클래스
@Service
public class ProductServiceImpl implements ProductService {

    // DAO 인터페이스 선언, @Autowired를 지정한 생성자를 통해 의존성 주입받음
    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    //--------------------------------------------------------------------------------------

    //인터페이스에서 정의한 메서드 오버라이딩
    @Override
    public ProductResponseDto getProduct(Long number) { //조회 메서드 구현
        Product product = productDAO.selectProduct(number);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }

    // 예제 6.24
    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) { //저장 메서드 - 상품정보 전달->애플리케이션->데이터베이스
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product savedProduct = productDAO.insertProduct(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(savedProduct.getNumber()); //데이터를 저장하면서 가져온 인덱스를 DTO에 담아 결과값으로 클라이언트에 전달
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setPrice(savedProduct.getPrice());
        productResponseDto.setStock(savedProduct.getStock());

        return productResponseDto;
    }

    // 예제 6.25
    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {//업데이트 메서드
        Product changedProduct = productDAO.updateProductName(number, name);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(changedProduct.getNumber());
        productResponseDto.setName(changedProduct.getName());
        productResponseDto.setPrice(changedProduct.getPrice());
        productResponseDto.setStock(changedProduct.getStock());

        return productResponseDto;
    }

    // 예제 6.26
    @Override
    public void deleteProduct(Long number) throws Exception { //삭제 메서드
        productDAO.deleteProduct(number);
    }
}