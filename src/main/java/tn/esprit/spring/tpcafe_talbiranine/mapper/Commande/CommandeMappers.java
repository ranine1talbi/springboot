package tn.esprit.spring.tpcafe_talbiranine.mapper.Commande;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.spring.tpcafe_talbiranine.dto.Commande.CommandeRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Commande.CommandeResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Commande;

@Mapper(componentModel = "spring")
public interface CommandeMappers {

    // Entity -> DTO Response
    CommandeResponse toDto(Commande commande);

    // DTO Request -> Entity
    @Mapping(target = "idCommande", ignore = true) // pour la création, l'ID est généré par la DB
    Commande toEntity(CommandeRequest request);
}
