package tn.esprit.spring.tpcafe_talbiranine.mapper.Promotion;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.spring.tpcafe_talbiranine.dto.Promotion.PromotionRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Promotion.PromotionResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Promotion;

@Mapper(componentModel = "spring")
public interface PromotionMappers {

    // Convertir une entité Promotion en DTO de réponse
    @Mapping(target = "articleIds", expression = "java(promotion.getArticles() != null ? promotion.getArticles().stream().map(a -> a.getIdArticle()).toList() : null)")
    PromotionResponse toDto(Promotion promotion);

    // Convertir un DTO de requête en entité Promotion
    @Mapping(target = "idPromotion", ignore = true)
    @Mapping(target = "articles", ignore = true) // on ignore la liste d'articles ici, elle sera gérée dans le service
    Promotion toEntity(PromotionRequest request);
}
