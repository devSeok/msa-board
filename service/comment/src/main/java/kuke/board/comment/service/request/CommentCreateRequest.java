package kuke.board.comment.service.request;


import lombok.Data;

@Data
public class CommentCreateRequest {
    private Long articleId;
    private String content;
    private Long parentCommentId;
    private Long writerId;
}
