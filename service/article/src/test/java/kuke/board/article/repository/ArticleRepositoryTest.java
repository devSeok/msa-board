package kuke.board.article.repository;

import kuke.board.article.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository  articleRepository;

    @Test
    void findAllTest() {
        List<Article> all = articleRepository.findAll(1L, 1499970L, 30L);

        log.info("size {}", all.size());

        for (Article article : all) {
            log.info("info {}", article);
        }
    }

    @Test
    void countTest() {
        Long count = articleRepository.count(1L, 10000L);

        log.info("count {}", count);
    }

    @Test
    void findInfiniteScrollTest() {
        List<Article> allInfiniteScroll = articleRepository.findAllInfiniteScroll(1L, 30L);

        for (Article article : allInfiniteScroll) {
            log.info("info {}", article.getArticleId());
        }

        Long article = allInfiniteScroll.get(allInfiniteScroll.size() - 1).getArticleId();

        List<Article> allInfiniteScroll2 = articleRepository.findAllInfiniteScroll(1L, 30L, article);

        for (Article articledd : allInfiniteScroll2) {
            log.info("info {}", articledd.getArticleId());
        }
    }

}