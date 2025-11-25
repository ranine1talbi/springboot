package tn.esprit.spring.tpcafe_talbiranine.dto.Article;

import lombok.*;
import tn.esprit.spring.tpcafe_talbiranine.entites.TypeArticle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleRequest {

    private String nomArticle;
    private float prixArticle;
    private TypeArticle typeArticle;

}
