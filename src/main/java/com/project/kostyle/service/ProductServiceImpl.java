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
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
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
                    List<ProductImgDto> imgs = m.getImgs().stream()
                            .map(ProductImgDto::of)
                            .filter(f -> !f.isDefault())
                            .collect(Collectors.toList());

                    ProductImgDto img = productImgRepository.findByDefault(true, m.getPno())
                            .map(ProductImgDto::of)
                            .orElseThrow(EntityNotFoundException::new);

                    return ProductDto.of(m, img ,imgs);
                });
    }

    @Override
    @Transactional
    public ProductDto findOne(Long pno, Cookie cookie, HttpServletResponse response) {

        Product product = productRepository.findById(pno).orElseThrow(EntityNotFoundException::new);

        // 히트카운트 증가
        if (cookie != null) {
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
            product.addHitCount();
        }


        List<ProductImgDto> imgs = product.getImgs().stream()
                .map(ProductImgDto::of)
                .filter(f -> !f.isDefault())
                .collect(Collectors.toList());

        ProductImgDto img = productImgRepository.findByDefault(true, product.getPno())
                .map(ProductImgDto::of)
                .orElseThrow(EntityNotFoundException::new);


        return ProductDto.of(product, img ,imgs);
    }

    @Transactional
    @Override
    public Long update(ProductDto dto) {
        Product product = productRepository.findById(dto.getPno()).orElseThrow(EntityNotFoundException::new);
        List<ProductImgDto> imgDtos = dto.getImgs();
        imgDtos.get(0).setDefault(true);
        SubCategory sc = subCategoryRepository.findById(dto.getScno()).orElseThrow(EntityNotFoundException::new);
        List<ProductImg> imgs = imgDtos.stream()
                .map(imgDto -> ProductImgDto.toEntity(imgDto, product))
                .collect(Collectors.toList());

        product.changeInfo(dto, imgs, sc);
        return product.getPno();
    }

    @Transactional
    @Override
    public Long delete(Long pno) {
        productRepository.deleteById(pno);
        return pno;
    }


}
