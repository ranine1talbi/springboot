package tn.esprit.spring.tpcafe_talbiranine.services.Commande;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafe_talbiranine.entites.Commande;
import tn.esprit.spring.tpcafe_talbiranine.repositories.CommandeRepository;
import java.util.List;

@Service
@AllArgsConstructor//pour faire communication entre 2
public class CommandeService implements ICommandeServices {

    private final CommandeRepository commandeRepository;

    @Override
    public Commande addCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    @Override
    public List<Commande> saveCommandes(List<Commande> commandes) {
        return commandeRepository.saveAll(commandes);
    }

    @Override
    public Commande selectCommandeById(long id) {
        return commandeRepository.findById(id).orElse(null);
    }

    @Override
    public Commande selectCommandeByIdWithGet(long id) {
        return commandeRepository.findById(id).get();
    }

    @Override
    public Commande selectCommandeByIdWithOrElse(long id) {
        Commande commandeParDefaut = Commande.builder()
                .totalCommande(0)
                .build();
        return commandeRepository.findById(id).orElse(commandeParDefaut);
    }

    @Override
    public List<Commande> selectAllCommandes() {return commandeRepository.findAll();}

    @Override
    public void deleteCommande(Commande commande) {commandeRepository.delete(commande);}

    @Override
    public void deleteAllCommandes() {commandeRepository.deleteAll();}

    @Override
    public void deleteCommandeById(long id) {commandeRepository.deleteById(id);}

    @Override
    public long countCommandes() {return commandeRepository.count();}

    @Override
    public boolean verifCommandeById(long id) {return commandeRepository.existsById(id);}
}
