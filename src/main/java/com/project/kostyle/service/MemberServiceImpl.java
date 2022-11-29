package com.project.kostyle.service;

import com.project.kostyle.dto.member.MemberDto;
import com.project.kostyle.entity.Member;
import com.project.kostyle.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService, UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    //회원가입
    @Override
    public Long create(MemberDto memberDto) {
        validateDuplicateMember(memberDto.getEmail()); //회원 중복 검사
        Long mno = memberRepository.save(Member.createMember(memberDto, passwordEncoder)).getMno();

        return mno;
    }

    //회원 중복 검사
    private void validateDuplicateMember(String email) {
        if(memberRepository.existsByEmail(email)){
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }
    }

    //로그인
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(email);

        if(member ==null){
            throw new UsernameNotFoundException("해당 이메일을 찾을 수 없습니다.");
        }
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getAuthority().toString())
                .build();
    }





}
