package tn.esprit.spring.tpcafe_talbiranine.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafe_talbiranine.dto.Article.ArticleRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Article.ArticleResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.TypeArticle;
import tn.esprit.spring.tpcafe_talbiranine.services.Article.IArticleServices;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("article")
@AllArgsConstructor
public class ArticleRestController {

    private final IArticleServices articleServices;

    // ================= CRUD de base =================
    @GetMapping
    public List<ArticleResponse> selectAllArticles() {
        return articleServices.selectAllArticles();
    }

    @PostMapping
    public ArticleResponse addArticle(@RequestBody ArticleRequest article) {
        return articleServices.addArticle(article);
    }

    @PostMapping("addarticles")
    public List<ArticleResponse> addArticles(@RequestBody List<ArticleRequest> articles) {
        return articleServices.saveArticles(articles);
    }

    @GetMapping("selectedById/{id}")
    public ArticleResponse selectArticleById(@PathVariable long id) {
        return articleServices.recupererArticleParId(id);
    }

    @DeleteMapping("deleteById/{id}")
    public void deleteArticleById(@PathVariable long id) {
        articleServices.deleteArticleById(id);
    }

    @DeleteMapping("deleteAll")
    public void deleteAllArticles() {
        articleServices.deleteAllArticles();
    }

    @GetMapping("count")
    public long countArticles() {
        return articleServices.countArticles();
    }

    @GetMapping("exists/{id}")
    public boolean verifArticleById(@PathVariable long id) {
        return articleServices.verifArticleById(id);
    }

    // ================= Recherches spécifiques =================

    @GetMapping("byName/{nom}")
    public List<ArticleResponse> findByNomExact(@PathVariable String nom) {
        return articleServices.findByNomExact(nom);
    }

    @GetMapping("byType/{type}")
    public List<ArticleResponse> findByType(@PathVariable TypeArticle type) {
        return articleServices.findByType(type);
    }

    @GetMapping("byPrix/{prix}")
    public List<ArticleResponse> findByPrix(@PathVariable float prix) {
        return articleServices.findByPrix(prix);
    }

    @GetMapping("existsByName/{nom}")
    public boolean existsByNom(@PathVariable String nom) {
        return articleServices.existsByNom(nom);
    }

    @GetMapping("countByType/{type}")
    public long countByType(@PathVariable TypeArticle type) {
        return articleServices.countByType(type);
    }

    @GetMapping("search")
    public List<ArticleResponse> searchByNomAndType(
            @RequestParam String mot,
            @RequestParam TypeArticle type) {
        return articleServices.findByNomContainsAndType(mot, type);
    }

    @GetMapping("searchByPrixAndTypes")
    public List<ArticleResponse> searchByPrixAndTypes(
            @RequestParam float min,
            @RequestParam float max,
            @RequestParam List<TypeArticle> types) {
        return articleServices.findByPrixBetweenAndTypes(min, max, types);
    }

    @GetMapping("startsWithIgnoreCase")
    public List<ArticleResponse> findByNomStartsWithIgnoreCase(
            @RequestParam String prefix) {
        return articleServices.findByNomStartsWithIgnoreCaseOrderByPrix(prefix);
    }

    @GetMapping("maxPrixByType/{type}")
    public Optional<ArticleResponse> findMaxPrixByType(@PathVariable TypeArticle type) {
        return articleServices.findMaxPrixByType(type);
    }

    @GetMapping("byNomOrType")
    public List<ArticleResponse> findByNomOrType(
            @RequestParam String nom,
            @RequestParam TypeArticle type) {
        return articleServices.findByNomOrTypeOrderByPrixDesc(nom, type);
    }

    @GetMapping("startsWith")
    public List<ArticleResponse> findByNomStartsWith(@RequestParam String prefix) {
        return articleServices.findByNomStartsWith(prefix);
    }

    @GetMapping("endsWith")
    public List<ArticleResponse> findByNomEndsWith(@RequestParam String suffix) {
        return articleServices.findByNomEndsWith(suffix);
    }

    @GetMapping("typeIsNull")
    public List<ArticleResponse> findByTypeIsNull() {
        return articleServices.findByTypeIsNull();
    }

    @GetMapping("prixIsNotNull")
    public List<ArticleResponse> findByPrixIsNotNull() {
        return articleServices.findByPrixIsNotNull();
    }

    @GetMapping("withActivePromotions")
    public List<ArticleResponse> findWithActivePromotions() {
        return articleServices.findWithActivePromotions();
    }

    @GetMapping("searchByNomAndPrix")
    public List<ArticleResponse> findByNomContainsAndPrixBetween(
            @RequestParam String mot,
            @RequestParam float min,
            @RequestParam float max) {
        return articleServices.findByNomContainsAndPrixBetween(mot, min, max);
    }
}