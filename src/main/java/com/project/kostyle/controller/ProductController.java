package com.project.kostyle.controller;

import com.project.kostyle.dto.product.ProductDto;
import com.project.kostyle.dto.product.ProductImgDto;
import com.project.kostyle.dto.product.SearchDto;
import com.project.kostyle.service.ProductService;
import com.project.kostyle.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final UploadService uploadService;

    // 등록
    @GetMapping("/create")
    public ResponseEntity<String> create(){
        return new ResponseEntity<>("상품등록 페이지", HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Long> create(ProductDto dto, MultipartFile[] multipartFiles) {
        List<ProductImgDto> imgs = uploadService.upload(multipartFiles);
        dto.setImgs(imgs);

        log.info("imgs = {}", imgs);

        return new ResponseEntity<>(productService.create(dto), HttpStatus.OK);
    }

    // 리스트 조회
    @GetMapping
    public ResponseEntity<Page<ProductDto>> findAll(Pageable pageable, SearchDto search) {
        log.info("search = {}", search);
        return new ResponseEntity<>(productService.findAll(pageable, search), HttpStatus.OK);
    }
}
