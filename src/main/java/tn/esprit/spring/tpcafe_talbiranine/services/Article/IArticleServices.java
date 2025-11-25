package tn.esprit.spring.tpcafe_talbiranine.services.Article;

import tn.esprit.spring.tpcafe_talbiranine.dto.Article.ArticleRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Article.ArticleResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Article;
import tn.esprit.spring.tpcafe_talbiranine.entites.Promotion;
import tn.esprit.spring.tpcafe_talbiranine.entites.TypeArticle;

import java.util.List;
import java.util.Optional;

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

    List<ArticleResponse> findByNomExact(String nom);
    List<ArticleResponse> findByType(TypeArticle type);
    List<ArticleResponse> findByPrix(float prix);
    boolean existsByNom(String nom);
    long countByType(TypeArticle type);
    List<ArticleResponse> findByNomContainsAndType(String mot, TypeArticle type);
    List<ArticleResponse> findByPrixBetweenAndTypes(float min, float max, List<TypeArticle> types);
    List<ArticleResponse> findByNomStartsWithIgnoreCaseOrderByPrix(String prefix);
    Optional<ArticleResponse> findMaxPrixByType(TypeArticle type);
    List<ArticleResponse> findByNomOrTypeOrderByPrixDesc(String nom, TypeArticle type);
    List<ArticleResponse> findByNomStartsWith(String prefix);
    List<ArticleResponse> findByNomEndsWith(String suffix);
    List<ArticleResponse> findByTypeIsNull();
    List<ArticleResponse> findByPrixIsNotNull();
    List<ArticleResponse> findWithActivePromotions();
    List<ArticleResponse> findByNomContainsAndPrixBetween(String mot, float min, float max);

    void ajouterPromtionEtAffecterArticle(Promotion p , long idArticle );
} //les methode mtaa crud extender m jpareposoitiries prer deffinin
