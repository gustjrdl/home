package tripboat.tripboat1.CommentFile;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CommentForm {

    @NotEmpty(message="내용을 입력 해주세요.")
    private String content;

}
