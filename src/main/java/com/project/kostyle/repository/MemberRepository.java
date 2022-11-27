package com.project.kostyle.repository;

import com.project.kostyle.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //중복 회원 검사
    boolean existsByEmail(String email); //쿼리메서드 (jpa에서 제공해주는)
}
