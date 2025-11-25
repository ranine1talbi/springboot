package tn.esprit.spring.tpcafe_talbiranine.repositories;

import tn.esprit.spring.tpcafe_talbiranine.entites.Client;
import tn.esprit.spring.tpcafe_talbiranine.entites.TypeArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByNomAndPrenom(String nom, String prenom);

    boolean existsByNom(String nom);

    long countByDateNaissanceAfter(LocalDate date);

    // ✅ Points fidélité – FIXED HERE
    List<Client> findByCarteFidelite_PointsAcumulesGreaterThan(int pts);
    List<Client> findByCarteFidelite_PointsAcumulesGreaterThanEqual(int pts);
    List<Client> findByCarteFidelite_PointsAcumulesBetween(int min, int max);

    // Articles
    // Find clients who bought an article with the exact name
    List<Client> findByCommandes_Details_Article_NomArticle(String articleName);

    // Find clients whose name contains a keyword and bought articles of a specific type
    List<Client> findByNomContainingAndCommandes_Details_Article_TypeArticle(String nom, TypeArticle type);


    // Autres méthodes
    List<Client> findByNom(String nom);
    List<Client> findByPrenom(String prenom);
    List<Client> findByNomContainingOrPrenomContaining(String nom, String prenom);
    List<Client> findByNomContainingAndPrenomContaining(String nom, String prenom);
    List<Client> findByDateNaissanceBetween(LocalDate start, LocalDate end);
    List<Client> findByNomStartingWithAndDateNaissanceBefore(String prefix, LocalDate date);
    List<Client> findByAdresse_Ville(String ville);
    List<Client> findByNomContainingOrderByPrenomAsc(String nom);
    List<Client> findByNomContainingOrderByPrenomDesc(String nom);
    List<Client> findByNomStartingWith(String nom);
    List<Client> findByPrenomEndingWith(String prenom);
    List<Client> findByDateNaissanceIsNull();
    List<Client> findByAdresseIsNotNull();
    List<Client> findByAdresse_VilleIn(List<String> villes);
}
