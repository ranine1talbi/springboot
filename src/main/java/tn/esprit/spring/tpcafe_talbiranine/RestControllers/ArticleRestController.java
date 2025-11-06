package tn.esprit.spring.tpcafe_talbiranine.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafe_talbiranine.dto.Article.ArticleRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Article.ArticleResponse;
import tn.esprit.spring.tpcafe_talbiranine.services.Article.IArticleServices;

import java.util.List;

@RestController
@RequestMapping("article")
@AllArgsConstructor
public class ArticleRestController {

    private final IArticleServices articleServices;

    // 🔹 GET : récupérer tous les articles
    @GetMapping
    public List<ArticleResponse> selectAllArticles() {
        return articleServices.selectAllArticles();
    }

    // 🔹 POST : ajouter un seul article
    @PostMapping
    public ArticleResponse addArticle(@RequestBody ArticleRequest article) {
        return articleServices.addArticle(article);
    }

    // 🔹 POST : ajouter plusieurs articles
    @PostMapping("addarticles")
    public List<ArticleResponse> addArticles(@RequestBody List<ArticleRequest> articles) {
        return articleServices.saveArticles(articles);
    }

    // 🔹 GET : récupérer un article par ID
    @GetMapping("selectedById/{id}")
    public ArticleResponse selectArticleById(@PathVariable long id) {
        return articleServices.recupererArticleParId(id);
    }

    // 🔹 DELETE : supprimer un article par ID
    @DeleteMapping("deleteById/{id}")
    public void deleteArticleById(@PathVariable long id) {
        articleServices.deleteArticleById(id);
    }

    // 🔹 DELETE : supprimer tous les articles
    @DeleteMapping("deleteAll")
    public void deleteAllArticles() {
        articleServices.deleteAllArticles();
    }

    // 🔹 GET : compter le nombre d’articles
    @GetMapping("count")
    public long countArticles() {
        return articleServices.countArticles();
    }

    // 🔹 GET : vérifier si un article existe par ID
    @GetMapping("exists/{id}")
    public boolean verifArticleById(@PathVariable long id) {
        return articleServices.verifArticleById(id);
    }
}
