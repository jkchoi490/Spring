package com.springboot.jpa.controller;

import com.springboot.jpa.data.dto.ChangeProductNameDto;
import com.springboot.jpa.data.dto.ProductDto;
import com.springboot.jpa.data.dto.ProductResponseDto;
import com.springboot.jpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 예제 6.27
@RestController
@RequestMapping("/product")
public class ProductController { //컨트롤러 생성 : 비즈니스 로직과 클라이언트의 요청 연결
    //클라이언트로부터 요청을 받고 해당 요청에 대해 서비스 레이어에 구현도니 적절한 메서드를 호출해서 결과값을 받음
    //컨트롤러 : 요청과 응답을 전달하는 역할
    //기능 요청 : 컨트롤러 -> 서비스 -> DAO -> 리포지토리로 이동 (응답 전달 시에는 역순으로 진행)

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<ProductResponseDto> getProduct(Long number) {
        ProductResponseDto productResponseDto = productService.getProduct(number);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PostMapping()
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto) {
        ProductResponseDto productResponseDto = productService.saveProduct(productDto);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PutMapping()
    public ResponseEntity<ProductResponseDto> changeProductName(
            @RequestBody ChangeProductNameDto changeProductNameDto) throws Exception {
        ProductResponseDto productResponseDto = productService.changeProductName(
                changeProductNameDto.getNumber(),
                changeProductNameDto.getName());

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);

    }

    @DeleteMapping()
    public ResponseEntity<String> deleteProduct(Long number) throws Exception {
        productService.deleteProduct(number);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

}