package tn.esprit.spring.tpcafe_talbiranine.mapper.Detail_Commande;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.spring.tpcafe_talbiranine.dto.Detail_Commande.Detail_CommandeRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Detail_Commande.Detail_CommandeResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Detail_Commande;

@Mapper(componentModel = "spring")
public interface Detail_CommandeMappers {

    // ✅ Mapping de l'entité vers le DTO de réponse
    @Mapping(source = "commande.idCommande", target = "commandeId")
    Detail_CommandeResponse toDto(Detail_Commande detailCommande);

    // ✅ Mapping du DTO de requête vers l'entité
    @Mapping(target = "idDetail_Commande", ignore = true)
    // L'association avec Commande et Article se fera côté service
    @Mapping(target = "commande", ignore = true)
    Detail_Commande toEntity(Detail_CommandeRequest request);
}
