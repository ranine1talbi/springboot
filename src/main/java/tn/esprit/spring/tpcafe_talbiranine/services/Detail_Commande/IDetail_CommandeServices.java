package tn.esprit.spring.tpcafe_talbiranine.services.Detail_Commande;

import tn.esprit.spring.tpcafe_talbiranine.dto.Detail_Commande.Detail_CommandeRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Detail_Commande.Detail_CommandeResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Detail_Commande;

import java.util.List;

public interface IDetail_CommandeServices {

    // ✅ Méthodes CRUD avec DTO
    Detail_CommandeResponse addDetailCommande(Detail_CommandeRequest request);
    List<Detail_CommandeResponse> saveDetailsCommande(List<Detail_CommandeRequest> requests);
    Detail_CommandeResponse recupererDetailCommandeParId(long id);
    List<Detail_CommandeResponse> selectAllDetailsCommande();

    // ✅ Méthodes de suppression et utils
    void deleteDetailCommandeById(long id);
    void deleteAllDetailsCommande();
    long countDetailsCommande();
    boolean verifDetailCommandeById(long id);

    // Optionnel : méthodes utilisant l'entité directement
    // Detail_Commande addDetailCommandeEntity(Detail_Commande detail);
    // List<Detail_Commande> saveDetailsCommandeEntity(List<Detail_Commande> details);
    // Detail_Commande selectDetailCommandeByIdWithGet(long id);
    // Detail_Commande selectDetailCommandeByIdWithOrElse(long id);
}
