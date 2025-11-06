package tn.esprit.spring.tpcafe_talbiranine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.tpcafe_talbiranine.entites.Detail_Commande;

@Repository
public interface DetailCommandeRepository extends JpaRepository<Detail_Commande, Long> {
}
