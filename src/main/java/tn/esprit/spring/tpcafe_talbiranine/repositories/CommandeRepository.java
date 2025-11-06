package tn.esprit.spring.tpcafe_talbiranine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.tpcafe_talbiranine.entites.Commande;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
