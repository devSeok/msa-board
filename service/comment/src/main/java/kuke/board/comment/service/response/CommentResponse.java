package kuke.board.comment.service.response;


import kuke.board.comment.entity.Comment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponse {
    private Long commentId;
    private String content;
    private Long parentCommentId;
    private Long articleId;
    private Long writerId;
    private Boolean deleted;
    private LocalDateTime createdAt;

    public static CommentResponse form(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setCommentId(comment.getCommentId());
        response.setContent(comment.getContent());
        response.setParentCommentId(comment.getParentCommentId());
        response.setArticleId(comment.getArticleId());
        response.setWriterId(comment.getWriterId());
        response.setDeleted(comment.getDeleted());
        response.setCreatedAt(comment.getCreatedAt());

        return response;
    }

}
