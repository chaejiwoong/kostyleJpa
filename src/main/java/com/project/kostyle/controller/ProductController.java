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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final UploadService uploadService;

    // 등록
    @PostMapping("/create")
    public ResponseEntity<Long> create(ProductDto dto, MultipartFile[] multipartFiles) {
        List<ProductImgDto> imgs = uploadService.upload(multipartFiles);
        dto.setImgs(imgs);

        log.info("imgs = {}", imgs);

        return new ResponseEntity<>(productService.create(dto), HttpStatus.OK);
    }

    // 리스트 조회
    @GetMapping
    public ResponseEntity<Page<ProductDto>> findAll(Pageable pageable, SearchDto search,
                                                    HttpServletResponse response) {
        Cookie cookie = new Cookie("hitCount", "hitCount");
        cookie.setPath("/");
        response.addCookie(cookie);

        log.info("search = {}", search);
        return new ResponseEntity<>(productService.findAll(pageable, search), HttpStatus.OK);
    }

    // 상품 상세
    @GetMapping("/{pno}")
    public ResponseEntity<ProductDto> findOne(@PathVariable Long pno,
                                              @CookieValue(name = "hitCount", required = false) Cookie cookie,
                                              HttpServletResponse response) {
        return new ResponseEntity<>(productService.findOne(pno, cookie, response), HttpStatus.OK);
    }

    // 상품 수정
    @PutMapping("/{pno}")
    public ResponseEntity<Long> update(@PathVariable Long pno, ProductDto dto, MultipartFile[] multipartFiles) {
        List<ProductImgDto> imgs = uploadService.upload(multipartFiles);
        dto.setPno(pno);
        dto.setImgs(imgs);

        log.info("imgs = {}", imgs);

        return new ResponseEntity<>(productService.update(dto), HttpStatus.OK);
    }

    // 상품 삭제
    @DeleteMapping("/{pno}")
    public ResponseEntity<Long> delete(@PathVariable Long pno) {
        return new ResponseEntity<>(productService.delete(pno), HttpStatus.OK);
    }
}
