package tn.esprit.spring.tpcafe_talbiranine.services.Detail_Commande;
import tn.esprit.spring.tpcafe_talbiranine.entites.Detail_Commande;
import java.util.List;

public interface IDetail_CommandeServices {
    Detail_Commande addDetailCommande(Detail_Commande detail);
    List<Detail_Commande> saveDetailsCommande(List<Detail_Commande> details);
    Detail_Commande selectDetailCommandeById(long id);
    Detail_Commande selectDetailCommandeByIdWithGet(long id);
    Detail_Commande selectDetailCommandeByIdWithOrElse(long id);
    List<Detail_Commande> selectAllDetailsCommande();
    void deleteDetailCommande(Detail_Commande detail);
    void deleteAllDetailsCommande();
    void deleteDetailCommandeById(long id);
    long countDetailsCommande();
    boolean verifDetailCommandeById(long id);

}
