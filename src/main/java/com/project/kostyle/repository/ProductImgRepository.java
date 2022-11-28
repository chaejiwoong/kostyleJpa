package com.project.kostyle.repository;

import com.project.kostyle.entity.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductImgRepository extends JpaRepository<ProductImg, Long> {

    @Query("select pi from ProductImg pi where pi.isDefault = :isDefault and pi.product.pno = :pno")
    Optional<ProductImg> findByDefault(@Param("isDefault") boolean isDefault,@Param("pno") Long pno);
}
