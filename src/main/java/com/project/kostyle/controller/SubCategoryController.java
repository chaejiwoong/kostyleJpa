package com.project.kostyle.controller;

import com.project.kostyle.dto.product.ParentCategoryDto;
import com.project.kostyle.dto.product.SubCategoryDto;
import com.project.kostyle.repository.SubCategoryRepository;
import com.project.kostyle.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/subCategories")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    // 등록
    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody SubCategoryDto dto) {
        return new ResponseEntity<>(subCategoryService.create(dto), HttpStatus.OK);
    }

    // 리스트 조회
    @GetMapping
    public ResponseEntity<List<SubCategoryDto>> findAll() {
        return new ResponseEntity<>(subCategoryService.findAll(), HttpStatus.OK);
    }

    // 상세 조회
    @GetMapping("/{pcno}")
    public ResponseEntity<SubCategoryDto> findOne(@PathVariable Long pcno) {
        return new ResponseEntity<>(subCategoryService.findOne(pcno), HttpStatus.OK);
    }

    // 수정
    @PutMapping("/{pcno}")
    public ResponseEntity<Long> update(@PathVariable Long scno, @RequestBody SubCategoryDto dto) {
        dto.setPcno(scno);
        return new ResponseEntity<>(subCategoryService.update(dto), HttpStatus.OK);
    }

    // 삭제
    @DeleteMapping("/{pcno}")
    public ResponseEntity<Long> delete(@PathVariable Long pcno) {
        return new ResponseEntity<>(subCategoryService.delete(pcno), HttpStatus.OK);
    }
}
