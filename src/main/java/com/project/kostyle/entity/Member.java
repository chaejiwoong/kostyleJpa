package com.project.kostyle.entity;

import com.project.kostyle.dto.member.MemberDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
@DynamicInsert
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(unique = true)
    private String email;

    private String name;

    private String password;

    @ColumnDefault("0")
    private Integer point;

    @Enumerated(EnumType.STRING)
    private Role authority;

    @OneToMany(mappedBy = "member")
    private List<Address> addressList; //얘는 편의성을 위해 엔티티에 넣은 것이지 Member 테이블에 생성되지는 않는다

    //MemberDto를 Member 엔티티로 변환해서 생성
    public static Member createMember(MemberDto memberDto, PasswordEncoder passwordEncoder){

        Member member = Member.builder()
                .email(memberDto.getEmail())
                .name(memberDto.getName())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .authority(Role.USER)
                .build();

        return member;
    }

}
