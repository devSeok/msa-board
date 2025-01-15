package kuke.board.article.service.response;


import lombok.Data;

import java.util.List;

@Data
public class ArticlePAgeResponse {
    private List<ArticleResponse> articles;
    private Long articleCount;

    public static ArticlePAgeResponse of(List<ArticleResponse> articles, Long articleCount) {
        ArticlePAgeResponse response = new ArticlePAgeResponse();

        response.articles = articles;
        response.articleCount = articleCount;

        return response;
    }
}
