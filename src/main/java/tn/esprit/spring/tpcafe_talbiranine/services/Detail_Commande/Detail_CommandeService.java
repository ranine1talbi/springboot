package tn.esprit.spring.tpcafe_talbiranine.services.Detail_Commande;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafe_talbiranine.dto.Detail_Commande.Detail_CommandeRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Detail_Commande.Detail_CommandeResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Commande;
import tn.esprit.spring.tpcafe_talbiranine.entites.Detail_Commande;
import tn.esprit.spring.tpcafe_talbiranine.mapper.Detail_Commande.Detail_CommandeMappers;
import tn.esprit.spring.tpcafe_talbiranine.repositories.CommandeRepository;
import tn.esprit.spring.tpcafe_talbiranine.repositories.DetailCommandeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class Detail_CommandeService implements IDetail_CommandeServices {

    private final DetailCommandeRepository detailCommandeRepository;
    private final CommandeRepository commandeRepository;
    private final Detail_CommandeMappers detailCommandeMappers;

    // ==================== Méthodes CRUD ====================

    @Override
    public Detail_CommandeResponse addDetailCommande(Detail_CommandeRequest request) {
        Detail_Commande detail = detailCommandeMappers.toEntity(request);

        Commande commande = commandeRepository.findById(request.getCommandeId())
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        detail.setCommande(commande);

        Detail_Commande saved = detailCommandeRepository.save(detail);
        return detailCommandeMappers.toDto(saved);
    }

    @Override
    public List<Detail_CommandeResponse> saveDetailsCommande(List<Detail_CommandeRequest> requests) {
        List<Detail_Commande> details = requests.stream()
                .map(request -> {
                    Detail_Commande detail = detailCommandeMappers.toEntity(request);
                    Commande commande = commandeRepository.findById(request.getCommandeId())
                            .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
                    detail.setCommande(commande);
                    return detail;
                })
                .collect(Collectors.toList());

        List<Detail_Commande> saved = detailCommandeRepository.saveAll(details);
        return saved.stream()
                .map(detailCommandeMappers::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Detail_CommandeResponse recupererDetailCommandeParId(long id) {
        Detail_Commande detail = detailCommandeRepository.findById(id).orElse(null);
        return (detail != null) ? detailCommandeMappers.toDto(detail) : null;
    }

    @Override
    public List<Detail_CommandeResponse> selectAllDetailsCommande() {
        return detailCommandeRepository.findAll().stream()
                .map(detailCommandeMappers::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDetailCommandeById(long id) {
        detailCommandeRepository.deleteById(id);
    }

    @Override
    public void deleteAllDetailsCommande() {
        detailCommandeRepository.deleteAll();
    }

    @Override
    public long countDetailsCommande() {
        return detailCommandeRepository.count();
    }

    @Override
    public boolean verifDetailCommandeById(long id) {
        return detailCommandeRepository.existsById(id);
    }

    // ==================== Méthodes avancées ====================

    public List<Detail_CommandeResponse> findByQuantiteArticle(int quantite) {
        return detailCommandeRepository.findByQuantiteArticle(quantite).stream()
                .map(detailCommandeMappers::toDto)
                .collect(Collectors.toList());
    }

    public List<Detail_CommandeResponse> findBySousTotalExact(float sousTotal) {
        return detailCommandeRepository.findBySousTotalDetailArticle(sousTotal).stream()
                .map(detailCommandeMappers::toDto)
                .collect(Collectors.toList());
    }

    public long countByQuantiteGreaterThan(int quantite) {
        return detailCommandeRepository.countByQuantiteArticleGreaterThan(quantite);
    }

    public boolean existsBySousTotalGreaterThan(float montant) {
        return detailCommandeRepository.existsBySousTotalDetailArticleGreaterThan(montant);
    }

    public List<Detail_CommandeResponse> findByQuantiteBetweenAndSousTotalMin(int minQte, int maxQte, float minSousTotal) {
        return detailCommandeRepository
                .findByQuantiteArticleBetweenAndSousTotalDetailArticleGreaterThan(minQte, maxQte, minSousTotal)
                .stream()
                .map(detailCommandeMappers::toDto)
                .collect(Collectors.toList());
    }

    public List<Detail_CommandeResponse> findBySousTotalBetweenOrderByQuantite(float min, float max) {
        return detailCommandeRepository.findBySousTotalDetailArticleBetweenOrderByQuantiteArticleAsc(min, max)
                .stream()
                .map(detailCommandeMappers::toDto)
                .collect(Collectors.toList());
    }

    public List<Detail_CommandeResponse> findBySousTotalApresPromoBetween(float min, float max) {
        return detailCommandeRepository.findBySousTotalDetailArticleApresPromoBetween(min, max)
                .stream()
                .map(detailCommandeMappers::toDto)
                .collect(Collectors.toList());
    }

    public List<Detail_CommandeResponse> findByQuantiteOrSousTotalMin(int quantite, float sousTotalMin) {
        return detailCommandeRepository.findByQuantiteArticleOrSousTotalDetailArticleGreaterThan(quantite, sousTotalMin)
                .stream()
                .map(detailCommandeMappers::toDto)
                .collect(Collectors.toList());
    }

    public List<Detail_CommandeResponse> findTop5MostExpensive() {
        return detailCommandeRepository.findTop5ByOrderBySousTotalDetailArticleDesc()
                .stream()
                .map(detailCommandeMappers::toDto)
                .collect(Collectors.toList());
    }

    public List<Detail_CommandeResponse> findWithoutQuantite() {
        return detailCommandeRepository.findByQuantiteArticleIsNull()
                .stream()
                .map(detailCommandeMappers::toDto)
                .collect(Collectors.toList());
    }

    public List<Detail_CommandeResponse> findWithSousTotalApresPromo() {
        return detailCommandeRepository.findBySousTotalDetailArticleApresPromoIsNotNull()
                .stream()
                .map(detailCommandeMappers::toDto)
                .collect(Collectors.toList());
    }

    public List<Detail_CommandeResponse> findAllWithCommandeAndArticle() {
        return detailCommandeRepository.findAllWithCommandeAndArticle()
                .stream()
                .map(detailCommandeMappers::toDto)
                .collect(Collectors.toList());
    }
}