package tn.esprit.spring.tpcafe_talbiranine.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.tpcafe_talbiranine.entites.CarteFidelite;

import java.time.LocalDate;
import java.util.List;

import jakarta.transaction.Transactional;

@Repository
public interface CarteFideliteRepository extends JpaRepository<CarteFidelite, Long> {

    // Trouver par points exacts
    List<CarteFidelite> findByPointsAcumules(Integer points);

    // Trouver par date de création
    List<CarteFidelite> findByDateCreation(LocalDate date);

    // Compter les cartes ayant plus de X points
    Long countByPointsAcumulesGreaterThan(Integer points);

    // Supprimer les cartes créées avant une certaine date
    @Modifying
    @Transactional
    void deleteByDateCreationBefore(LocalDate date);

    // Trouver les cartes avec des points entre min et max après une date
    List<CarteFidelite> findByPointsAcumulesBetweenAndDateCreationAfter(Integer minPoints, Integer maxPoints, LocalDate date);

    // Trouver les cartes avec points supérieurs ou égaux, triées par date
    List<CarteFidelite> findByPointsAcumulesGreaterThanEqualOrderByDateCreationAsc(Integer points);

    // Trouver les cartes créées entre deux dates
    List<CarteFidelite> findByDateCreationBetween(LocalDate start, LocalDate end);

    // Trouver les cartes avec moins de points ou créées avant une date
    List<CarteFidelite> findByPointsAcumulesLessThanOrDateCreationLessThan(Integer points, LocalDate date);

    // Trouver la carte avec le plus de points
    CarteFidelite findTopByOrderByPointsAcumulesDesc();

    // Trouver les cartes sans date de création
    List<CarteFidelite> findByDateCreationIsNull();

    // Trouver les cartes avec points définis
    List<CarteFidelite> findByPointsAcumulesIsNotNull();

    // Trouver les cartes par nom et prénom du client
    List<CarteFidelite> findByClientNomAndClientPrenom(String nom, String prenom);

    // Top 5 cartes par points
    List<CarteFidelite> findTop5ByOrderByPointsAcumulesDesc(Pageable pageable);
}
