package tn.esprit.spring.tpcafe_talbiranine.services.Commande;

import tn.esprit.spring.tpcafe_talbiranine.entites.Commande;
import java.util.List;

public interface ICommandeServices {

    Commande addCommande(Commande commande);

    List<Commande> saveCommandes(List<Commande> commandes);

    Commande selectCommandeById(long id);

    Commande selectCommandeByIdWithGet(long id);

    Commande selectCommandeByIdWithOrElse(long id);

    List<Commande> selectAllCommandes();

    void deleteCommande(Commande commande);

    void deleteAllCommandes();

    void deleteCommandeById(long id);

    long countCommandes();

    boolean verifCommandeById(long id);
}
