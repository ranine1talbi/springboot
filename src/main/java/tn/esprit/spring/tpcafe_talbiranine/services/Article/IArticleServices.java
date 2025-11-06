package tn.esprit.spring.tpcafe_talbiranine.services.Article;

import tn.esprit.spring.tpcafe_talbiranine.dto.Article.ArticleRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Article.ArticleResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Article;

import java.util.List;

public interface IArticleServices {
    ArticleResponse addArticle(ArticleRequest articleRequest);
    List<ArticleResponse> saveArticles(List<ArticleRequest> articleRequests);
    ArticleResponse recupererArticleParId(long id);
    List<ArticleResponse> selectAllArticles();
    void deleteArticle(Article article);
    void deleteAllArticles();
    void deleteArticleById(long id);
    long countArticles();
    boolean verifArticleById(long id);
}
