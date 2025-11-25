package tn.esprit.spring.tpcafe_talbiranine.mapper.Client;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.spring.tpcafe_talbiranine.dto.Client.ClientRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Client.ClientResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Client;
import tn.esprit.spring.tpcafe_talbiranine.mapper.Adresse.AdresseMappers;

@Mapper(componentModel = "spring", uses = {AdresseMappers.class})
public interface ClientMappers {

    // Convertit une entité Client en DTO de réponse
    ClientResponse toDto(Client client);

    // Convertit un DTO de requête en entité Client
    @Mapping(target = "idClient", ignore = true)
    @Mapping(target = "carteFidelite", ignore = true)
    @Mapping(target = "commandes", ignore = true)
    Client toEntity(ClientRequest request);
}
