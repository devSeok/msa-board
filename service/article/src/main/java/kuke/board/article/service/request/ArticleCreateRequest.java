package kuke.board.article.service.request;


import lombok.Data;

@Data
public class ArticleCreateRequest {

    private String title;
    private String content;
    private Long writerId;
    private Long boardId;
}
