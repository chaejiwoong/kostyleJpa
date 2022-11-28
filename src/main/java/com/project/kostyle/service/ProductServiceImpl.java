package com.project.kostyle.service;

import com.project.kostyle.dto.product.ProductDto;
import com.project.kostyle.dto.product.ProductImgDto;
import com.project.kostyle.dto.product.SearchDto;
import com.project.kostyle.entity.Product;
import com.project.kostyle.entity.ProductImg;
import com.project.kostyle.entity.SubCategory;
import com.project.kostyle.repository.ProductImgRepository;
import com.project.kostyle.repository.ProductRepository;
import com.project.kostyle.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductImgRepository productImgRepository;
    private final SubCategoryRepository subCategoryRepository;

    @Override
    @Transactional
    public Long create(ProductDto dto) {
        SubCategory sc = subCategoryRepository.findById(dto.getScno())
                .orElseThrow(EntityExistsException::new);
        Product product = ProductDto.toEntity(dto, sc);
        Long pno = productRepository.save(product).getPno();

        List<ProductImgDto> imgs = dto.getImgs();
        imgs.get(0).setDefault(true);
        for (ProductImgDto img : imgs) {
            productImgRepository.save(ProductImgDto.toEntity(img, product));
        }

        return pno;
    }

    @Override
    public Page<ProductDto> findAll(Pageable pageable, SearchDto search) {
        return productRepository.findAllByDsl(pageable, search)
                .map(m -> {
                    List<ProductImgDto> imgs = m.getImgs().stream().map(ProductImgDto::of).collect(Collectors.toList());
                    return ProductDto.of(m, imgs);
                });
    }


}
