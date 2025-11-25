package tn.esprit.spring.tpcafe_talbiranine.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.tpcafe_talbiranine.entites.Adresse;

import java.util.List;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long> {


    List<Adresse> findByVille(String ville);
        // 1. Trouver les adresses par code postal exact
        List<Adresse> findByCodePostal(int codePostal);

        // 2. Compter le nombre d'adresses dans une ville
        long countByVille(String ville);

        // 3. Supprimer toutes les adresses d'une ville
        void deleteByVille(String ville);

        // 4. Trouver les adresses d'une ville avec un code postal spécifique
        List<Adresse> findByVilleAndCodePostal(String ville, int codePostal);

        // 5. Trouver les adresses dont la rue contient un mot (insensible à la casse)
        List<Adresse> findByRueContainingIgnoreCase(String mot);

        // 6. Trouver les adresses situées dans une liste de villes
        List<Adresse> findByVilleIn(List<String> villes);

        // 7. Trouver les adresses avec un code postal dans une plage
        List<Adresse> findByCodePostalBetween(int min, int max);

        // 8. Trouver les adresses avec un code postal > paramètre
        List<Adresse> findByCodePostalGreaterThan(int codePostal);

        // 9. Trouver les adresses avec un code postal >= paramètre
        List<Adresse> findByCodePostalGreaterThanEqual(int codePostal);

        // 10. Trouver les adresses avec un code postal < paramètre
        List<Adresse> findByCodePostalLessThan(int codePostal);

        // 11. Trouver les adresses avec un code postal <= paramètre
        List<Adresse> findByCodePostalLessThanEqual(int codePostal);

        // 12. Trouver les adresses dont la rue commence par, dans une ville, triées par code postal
        List<Adresse> findByRueStartingWithAndVilleOrderByCodePostal(String rue, String ville);

        // 13. Trouver les adresses dont la rue commence par une chaîne
        List<Adresse> findByRueStartingWith(String prefix);

        // 14. Trouver les adresses dont la ville se termine par une terminaison
        List<Adresse> findByVilleEndingWith(String suffix);

        // 15. Trouver les adresses où la rue est null
        List<Adresse> findByRueIsNull();

        // 16. Trouver les adresses où la ville n'est pas null
        List<Adresse> findByVilleIsNotNull();
    }



