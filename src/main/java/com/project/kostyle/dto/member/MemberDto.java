package com.project.kostyle.dto.member;

import com.project.kostyle.entity.Address;
import com.project.kostyle.entity.Member;
import com.project.kostyle.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

//@Builder
@Getter
@Setter
@Builder
public class MemberDto {

    private Long mno;

    @NotBlank(message = "이메일을 입력하세요.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotEmpty(message = "이름을 입력하세요")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,10}$" , message = "이름은 특수문자를 포함하지 않은 2~10자리여야 합니다.")
    private String name;

    @NotEmpty(message = "비밀번호를 입력하세요")
    @Length(min = 8, max = 16, message = "비밀번호는 8~16자리여야 합니다.")
    private String password;

    private Integer point;

    private Role authority;

    private List<Address> addressList;


}
