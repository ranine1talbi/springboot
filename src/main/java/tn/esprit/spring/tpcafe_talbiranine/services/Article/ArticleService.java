package tn.esprit.spring.tpcafe_talbiranine.services.Article;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafe_talbiranine.dto.Article.ArticleRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Article.ArticleResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Article;
import tn.esprit.spring.tpcafe_talbiranine.entites.Promotion;
import tn.esprit.spring.tpcafe_talbiranine.entites.TypeArticle;
import tn.esprit.spring.tpcafe_talbiranine.mapper.Article.ArticleMappers;
import tn.esprit.spring.tpcafe_talbiranine.repositories.ArticleRepository;
import tn.esprit.spring.tpcafe_talbiranine.repositories.PromotionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleService implements IArticleServices {

    private final ArticleRepository articleRepository;
    private final ArticleMappers articleMapper;
    private final PromotionRepository promotionRepository;

    @Override
    public ArticleResponse addArticle(ArticleRequest articleRequest) {
        Article article = articleMapper.toEntity(articleRequest);
        Article saved = articleRepository.save(article);
        return articleMapper.toDto(saved);
    }

    @Override
    public List<ArticleResponse> saveArticles(List<ArticleRequest> articleRequests) {
        List<Article> articles = articleRequests.stream()
                .map(articleMapper::toEntity)
                .collect(Collectors.toList());
        List<Article> saved = articleRepository.saveAll(articles);
        return saved.stream().map(articleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ArticleResponse recupererArticleParId(long id) {
        return articleRepository.findById(id)
                .map(articleMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<ArticleResponse> selectAllArticles() {
        return articleRepository.findAll().stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList());
    }

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

    @Override
    public long countArticles() {
        return articleRepository.count();
    }

    @Override
    public boolean verifArticleById(long id) {
        return articleRepository.existsById(id);
    }

    @Override
    public List<ArticleResponse> findByNomExact(String nom) {
        return articleRepository.findByNomArticle(nom).stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> findByType(TypeArticle type) {
        return articleRepository.findByTypeArticle(type).stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> findByPrix(float prix) {
        return articleRepository.findByPrixArticle(prix).stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByNom(String nom) {
        return articleRepository.existsByNomArticle(nom);
    }

    @Override
    public long countByType(TypeArticle type) {
        return articleRepository.countByTypeArticle(type);
    }

    @Override
    public List<ArticleResponse> findByNomContainsAndType(String mot, TypeArticle type) {
        return articleRepository.findByNomArticleContainingIgnoreCaseAndTypeArticle(mot, type)
                .stream().map(articleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> findByPrixBetweenAndTypes(float min, float max, List<TypeArticle> types) {
        return articleRepository.findByPrixArticleBetweenAndTypeArticleIn(min, max, types)
                .stream().map(articleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> findByNomStartsWithIgnoreCaseOrderByPrix(String prefix) {
        return articleRepository.findByNomArticleStartingWithIgnoreCaseOrderByPrixArticleAsc(prefix)
                .stream().map(articleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<ArticleResponse> findMaxPrixByType(TypeArticle type) {
        return articleRepository.findTopByTypeArticleOrderByPrixArticleDesc(type)
                .map(articleMapper::toDto);
    }

    @Override
    public List<ArticleResponse> findByNomOrTypeOrderByPrixDesc(String nom, TypeArticle type) {
        return articleRepository.findByNomArticleOrTypeArticleOrderByPrixArticleDesc(nom, type)
                .stream().map(articleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> findByNomStartsWith(String prefix) {
        return articleRepository.findByNomArticleStartingWith(prefix)
                .stream().map(articleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> findByNomEndsWith(String suffix) {
        return articleRepository.findByNomArticleEndingWith(suffix)
                .stream().map(articleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> findByTypeIsNull() {
        return articleRepository.findByTypeArticleIsNull()
                .stream().map(articleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> findByPrixIsNotNull() {
        return articleRepository.findByPrixArticleIsNotNull()
                .stream().map(articleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> findWithActivePromotions() {
        return articleRepository.findArticlesWithActivePromotions()
                .stream().map(articleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponse> findByNomContainsAndPrixBetween(String mot, float min, float max) {
        return articleRepository.findByNomContainingAndPrixBetween(mot, min, max)
                .stream().map(articleMapper::toDto).collect(Collectors.toList());
    }
    @Override
    public void ajouterPromtionEtAffecterArticle(Promotion promotion , long idArticle ){
        Article article = articleRepository.findById(idArticle).get();
        article.getPromotions().add(promotion);
        articleRepository.save(article);
    }
}
