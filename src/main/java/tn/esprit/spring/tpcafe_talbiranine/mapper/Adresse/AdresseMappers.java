package tn.esprit.spring.tpcafe_talbiranine.mapper.Adresse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.spring.tpcafe_talbiranine.dto.Adresse.AdresseRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Adresse.AdresseResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Adresse;

@Mapper(componentModel ="spring")
public interface AdresseMappers {
    AdresseResponse toDto(Adresse adresse);


    @Mapping(target = "idAdresse", ignore = true)
    Adresse toEntity(AdresseRequest Request);

}
