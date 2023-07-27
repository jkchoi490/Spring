package com.springboot.jpa.service;

import com.springboot.jpa.data.dto.ProductDto;
import com.springboot.jpa.data.dto.ProductResponseDto;

// 예제 6.21
public interface ProductService { //서비스 인터페이스

    // CRUD 기능
    ProductResponseDto getProduct(Long number);

    ProductResponseDto saveProduct(ProductDto productDto);

    ProductResponseDto changeProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;

}