package kuke.board.article.service.response;

import kuke.board.article.entity.Article;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleResponse {
    private Long articleId;
    private String title;
    private String content;
    private Long boardId;
    private Long writerId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static ArticleResponse of(Article article) {
        ArticleResponse response = new ArticleResponse();

        response.setArticleId(article.getArticleId());
        response.setTitle(article.getTitle());
        response.setContent(article.getContent());
        response.setBoardId(article.getBoardId());
        response.setWriterId(article.getWriterId());
        response.setCreatedAt(article.getCreatedAt());
        response.setModifiedAt(article.getModifiedAt());

        return response;
    }
}
