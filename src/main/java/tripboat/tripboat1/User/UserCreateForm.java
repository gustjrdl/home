package tripboat.tripboat1.User;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class UserCreateForm {

    @NotBlank(message = "아이디가 입력되지 않았습니다.")
    @Pattern(regexp = "^[a-zA-Z0-9].{4,16}", message = "아이디는 영문 대/소문자와 숫자로 4~16글자를 사용하세요")
    private String username;

    @NotBlank(message = "비밀번호가 입력되지 않았습니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password1;

    @NotBlank(message = "2차 비밀번호가 입력되지 않았습니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password2;

    @NotBlank(message = "이메일이 입력되지 않았습니다.")
    @Email(message = "이메일 형식을 확인하세요")
    private String email;

    @NotBlank(message = "닉네임이 입력되지 않았습니다.")
    @Pattern(regexp = "[가-힣].{2,10}", message = "닉네임은 2~10 한글로 사용하세요")
    private String nickname;
}