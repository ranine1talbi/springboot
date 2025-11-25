package tn.esprit.spring.tpcafe_talbiranine.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.tpcafe_talbiranine.entites.Article;
import tn.esprit.spring.tpcafe_talbiranine.entites.TypeArticle;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    // 1. Trouver les articles par nom exact
    List<Article> findByNomArticle(String nomArticle);

    // 2. Trouver les articles par type
    List<Article> findByTypeArticle(TypeArticle typeArticle);

    // Avec pagination
    Page<Article> findByTypeArticle(TypeArticle typeArticle, Pageable pageable);

    // 3. Trouver les articles par prix exact
    List<Article> findByPrixArticle(float prixArticle);

    // 4. Vérifier l'existence d'un article par nom
    boolean existsByNomArticle(String nomArticle);

    // 5. Compter les articles par type
    long countByTypeArticle(TypeArticle typeArticle);

    // 6. Trouver les articles dont le nom contient un mot et sont d'un type spécifique (insensible à la casse)
    List<Article> findByNomArticleContainingIgnoreCaseAndTypeArticle(String mot, TypeArticle typeArticle);

    // 7. Trouver les articles avec un prix dans une plage et de types spécifiques
    List<Article> findByPrixArticleBetweenAndTypeArticleIn(float min, float max, List<TypeArticle> types);

    // 8. Trouver les articles dont le nom commence par un préfixe (insensible à la casse), triés par prix
    List<Article> findByNomArticleStartingWithIgnoreCaseOrderByPrixArticleAsc(String prefix);

    // 9. Trouver les articles d'un type avec un prix maximum ou minimum
    Optional<Article> findTopByTypeArticleOrderByPrixArticleDesc(TypeArticle typeArticle);
    Optional<Article> findTopByTypeArticleOrderByPrixArticleAsc(TypeArticle typeArticle);

    // 10. Trouver les articles par nom ou type, triés par prix décroissant
    List<Article> findByNomArticleOrTypeArticleOrderByPrixArticleDesc(String nomArticle, TypeArticle typeArticle);

    // 11. Trouver les articles dont le nom commence par un préfixe spécifique
    List<Article> findByNomArticleStartingWith(String prefix);

    // 12. Trouver les articles dont le nom se termine par un suffixe
    List<Article> findByNomArticleEndingWith(String suffix);

    // 13. Trouver les articles sans type renseigné
    List<Article> findByTypeArticleIsNull();

    // 14. Trouver les articles avec un prix renseigné
    List<Article> findByPrixArticleIsNotNull();

    // 15. Trouver les articles avec leurs promotions actives
    @Query("""
       SELECT a
       FROM Article a
       JOIN a.promotions p
       WHERE CURRENT_DATE BETWEEN p.dateDebutPromo AND p.dateFinPromo
    """)
    List<Article> findArticlesWithActivePromotions();

    // 16. Trouver les articles avec nom contenant une chaine et prix dans une plage avec JPQL
    @Query("SELECT a FROM Article a WHERE a.nomArticle LIKE %:mot% AND a.prixArticle BETWEEN :min AND :max")
    List<Article> findByNomContainingAndPrixBetween(@Param("mot") String mot,
                                                    @Param("min") float min,
                                                    @Param("max") float max);
}
