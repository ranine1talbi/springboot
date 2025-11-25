package tn.esprit.spring.tpcafe_talbiranine.mapper.CartFidelite;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.spring.tpcafe_talbiranine.dto.CartFidelite.CartFideliteRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.CartFidelite.CartFideliteResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.CarteFidelite;

@Mapper(componentModel = "spring")
public interface CartFideliteMappers {
    @Mapping(source = "client.idClient", target = "clientId")
    CartFideliteResponse toDto(CarteFidelite carteFidelite);

    @Mapping(target = "idCarteFidelite", ignore = true)
    @Mapping(target = "client", ignore = true) // on gère souvent le client à part via son ID
    CarteFidelite toEntity(CartFideliteRequest request);
}
