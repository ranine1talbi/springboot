package tn.esprit.spring.tpcafe_talbiranine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.tpcafe_talbiranine.entites.Adresse;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> { /// genere le meth
}
