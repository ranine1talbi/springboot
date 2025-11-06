package tn.esprit.spring.tpcafe_talbiranine.services.Detail_Commande;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafe_talbiranine.entites.Detail_Commande;
import tn.esprit.spring.tpcafe_talbiranine.repositories.DetailCommandeRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class Detail_CommandeService implements IDetail_CommandeServices {

    private final DetailCommandeRepository detailCommandeRepository;

    @Override
    public Detail_Commande addDetailCommande(Detail_Commande detail) {
        return detailCommandeRepository.save(detail);
    }

    @Override
    public List<Detail_Commande> saveDetailsCommande(List<Detail_Commande> details) {
        return detailCommandeRepository.saveAll(details);
    }

    @Override
    public Detail_Commande selectDetailCommandeById(long id) {
        return detailCommandeRepository.findById(id).orElse(null);
    }

    @Override
    public Detail_Commande selectDetailCommandeByIdWithGet(long id) {
        return detailCommandeRepository.findById(id).get();
    }

    @Override
    public Detail_Commande selectDetailCommandeByIdWithOrElse(long id) {
        Detail_Commande detail = Detail_Commande.builder()
                .quantiteArticle(0)
                .sousTotalDetailArticle(0)
                .sousTotalDetailArticleApresPromo(0)
                .build();
        return detailCommandeRepository.findById(id).orElse(detail);
    }

    @Override
    public List<Detail_Commande> selectAllDetailsCommande() {
        return detailCommandeRepository.findAll();
    }

    @Override
    public void deleteDetailCommande(Detail_Commande detail) {
        detailCommandeRepository.delete(detail);
    }

    @Override
    public void deleteAllDetailsCommande() {
        detailCommandeRepository.deleteAll();
    }

    @Override
    public void deleteDetailCommandeById(long id) {
        detailCommandeRepository.deleteById(id);
    }

    @Override
    public long countDetailsCommande() {
        return detailCommandeRepository.count();
    }

    @Override
    public boolean verifDetailCommandeById(long id) {
        return detailCommandeRepository.existsById(id);
    }
}
