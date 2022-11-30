package com.project.kostyle.config;

import com.project.kostyle.entity.Member;
import com.project.kostyle.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final MemberRepository memberRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession();

        session.setAttribute("member", memberRepository.findByMno(Long.parseLong(authentication.getName())));

        //session에 class를 담고 session을 꺼내면 object로 나오므로 반드시 class타입으로 형변환이 필요하다
        Member member = (Member)session.getAttribute("member");

        session.setAttribute("greeting", member.getEmail() + "님 반갑습니다.");
        session.setAttribute("email", member.getEmail());

        session.setMaxInactiveInterval(60*60); //1시간동안 세션 유지 (로그인 유지)

        System.out.println(session.getAttribute("email") + " 로그인 성공");

        response.sendRedirect("/main");
    }
}
