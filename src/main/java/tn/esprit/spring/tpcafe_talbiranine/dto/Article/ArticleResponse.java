package tn.esprit.spring.tpcafe_talbiranine.dto.Article;

import lombok.*;
import tn.esprit.spring.tpcafe_talbiranine.entites.TypeArticle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleResponse {

    private long idArticle;
    private String nomArticle;
    private float prixArticle;
    private TypeArticle typeArticle;

}
