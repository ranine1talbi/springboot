package tn.esprit.spring.tpcafe_talbiranine.mapper.Article;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.spring.tpcafe_talbiranine.dto.Article.ArticleRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Article.ArticleResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Article;

@Mapper(componentModel = "spring")
public interface ArticleMappers {

    ArticleResponse toDto(Article article);

    @Mapping(target = "idArticle", ignore = true)
    @Mapping(target = "detailCommandes", ignore = true)
    @Mapping(target = "promotions", ignore = true)
    Article toEntity(ArticleRequest request);
}
