package kuke.board.article.api;

import kuke.board.article.service.response.ArticlePAgeResponse;
import kuke.board.article.service.response.ArticleResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

public class ArticleApiTest {
    RestClient restClient = RestClient.create("http://localhost:9000");

    @Test
    void createTest() {
        ArticleResponse articleResponse = create(new ArticleCreateRequest(
                "hi", "my content", 1L, 1L
        ));

        System.out.println(articleResponse);
    }

    ArticleResponse create(ArticleCreateRequest request) {
            return restClient.post()
                    .uri("/v1/articles")
                    .body(request)
                    .retrieve()
                    .body(ArticleResponse.class);
    }

    @Test
    void readTest() {
        ArticleResponse read = read(137382542299934720L);

        System.out.println(read);
    }

    ArticleResponse read(Long articleId) {
        return restClient.get()
                .uri("v1/articles/{articleId}", articleId)
                .retrieve()
                .body(ArticleResponse.class);
    }

    @Test
    void updateTest() {
        update(137382542299934720L);
        ArticleResponse read = read(137382542299934720L);

        System.out.println(read);
    }

    void update(Long articleId) {
        restClient.put()
                .uri("/v1/articles/{articleId}", articleId)
                .body(new ArticleUpdateRequest("hi 2", "my content 2"))
                .retrieve();
    }

    @Test
    void deleteTest() {
        restClient.delete()
                .uri("/v1/articles/{articleId}", 137382542299934720L)
                .retrieve();
    }

    @Test
    void readAllTest() {
        ArticlePAgeResponse response = restClient.get()
                .uri("/v1/articles?boardId=1&pageSize=30&page=50000")
                .retrieve()
                .body(ArticlePAgeResponse.class);

        System.out.println("count" + response.getArticleCount());

        for (ArticleResponse articleResponse : response.getArticles()) {
            System.out.println(articleResponse.getArticleId());
        }
    }

    @Test
    void readAllInfiniteScrollTest() {
        List<ArticleResponse> body = restClient.get()
                .uri("/v1/articles/infinite-scroll?boardId=1&pageSize=5")
                .retrieve()
                .body(new ParameterizedTypeReference<List<ArticleResponse>>() {
                });

        System.out.println("firestPage");
        for (ArticleResponse articleResponse : body) {
            System.out.println(articleResponse.getArticleId());
        }

        Long lastArticleId = body.get(body.size() - 1).getArticleId();

        List<ArticleResponse> body2 = restClient.get()
                .uri("/v1/articles/infinite-scroll?boardId=1&pageSize=5&limit=10&lastArticleId=%s".formatted(lastArticleId))
                .retrieve()
                .body(new ParameterizedTypeReference<List<ArticleResponse>>() {
                });

        for (ArticleResponse articleResponse : body2) {
            System.out.println(articleResponse.getArticleId());
        }
    }




    @Data
    @AllArgsConstructor
    static class ArticleCreateRequest {

        private String title;
        private String content;
        private Long writerId;
        private Long boardId;
    }

    @Data
    @AllArgsConstructor
    static class ArticleUpdateRequest {

        private String title;
        private String content;
    }
}
