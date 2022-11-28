package com.project.kostyle.controller;

import com.project.kostyle.dto.product.ParentCategoryDto;
import com.project.kostyle.service.ParentCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/parentCategories")
public class ParentCategoryController {

    private final ParentCategoryService parentCategoryService;

    // 등록
    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody ParentCategoryDto dto) {
        return new ResponseEntity<>(parentCategoryService.create(dto), HttpStatus.OK);
    }

    // 리스트 조회
    @GetMapping
    public ResponseEntity<List<ParentCategoryDto>> findAll() {
        return new ResponseEntity<>(parentCategoryService.findAll(), HttpStatus.OK);
    }

    // 상세 조회
    @GetMapping("/{pcno}")
    public ResponseEntity<ParentCategoryDto> findOne(@PathVariable Long pcno) {
        return new ResponseEntity<>(parentCategoryService.findOne(pcno), HttpStatus.OK);
    }

    // 수정
    @PutMapping("/{pcno}")
    public ResponseEntity<Long> update(@PathVariable Long pcno, @RequestBody ParentCategoryDto dto) {
        dto.setPcno(pcno);
        return new ResponseEntity<>(parentCategoryService.update(dto), HttpStatus.OK);
    }

    // 삭제
    @DeleteMapping("/{pcno}")
    public ResponseEntity<Long> delete(@PathVariable Long pcno) {
        return new ResponseEntity<>(parentCategoryService.delete(pcno), HttpStatus.OK);
    }
}
