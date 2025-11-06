package tn.esprit.spring.tpcafe_talbiranine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.tpcafe_talbiranine.entites.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
}
