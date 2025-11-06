package tn.esprit.spring.tpcafe_talbiranine.services.Article;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafe_talbiranine.dto.Article.ArticleRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Article.ArticleResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Article;
import tn.esprit.spring.tpcafe_talbiranine.mapper.Article.ArticleMappers;
import tn.esprit.spring.tpcafe_talbiranine.repositories.ArticleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleService implements IArticleServices {

    private final ArticleRepository articleRepository;
    private final ArticleMappers articleMapper;

    // ✅ Ajouter un article via DTO
    @Override
    public ArticleResponse addArticle(ArticleRequest articleRequest) {
        Article article = articleMapper.toEntity(articleRequest);
        Article saved = articleRepository.save(article);
        return articleMapper.toDto(saved);
    }

    // ✅ Récupérer un article par ID
    @Override
    public ArticleResponse recupererArticleParId(long id) {
        Article article = articleRepository.findById(id).orElse(null);
        return (article != null) ? articleMapper.toDto(article) : null;
    }

    // ✅ Ajouter plusieurs articles
    @Override
    public List<ArticleResponse> saveArticles(List<ArticleRequest> articleRequests) {
        List<Article> articles = articleRequests.stream()
                .map(articleMapper::toEntity)
                .collect(Collectors.toList());
        List<Article> saved = articleRepository.saveAll(articles);
        return saved.stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList());
    }

    // ✅ Récupérer tous les articles
    @Override
    public List<ArticleResponse> selectAllArticles() {
        return articleRepository.findAll()
                .stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList());
    }

    // ✅ Supprimer un article
    @Override
    public void deleteArticle(Article article) {
        articleRepository.delete(article);
    }

    @Override
    public void deleteAllArticles() {
        articleRepository.deleteAll();
    }

    @Override
    public void deleteArticleById(long id) {
        articleRepository.deleteById(id);
    }

    // ✅ Compter les articles
    @Override
    public long countArticles() {
        return articleRepository.count();
    }

    // ✅ Vérifier l’existence d’un article
    @Override
    public boolean verifArticleById(long id) {
        return articleRepository.existsById(id);
    }
}
