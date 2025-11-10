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

    // ✅ Ajouter un détail commande via DTO
    @Override
    public Detail_CommandeResponse addDetailCommande(Detail_CommandeRequest request) {
        Detail_Commande detail = detailCommandeMappers.toEntity(request);

        Commande commande = commandeRepository.findById(request.getCommandeId())
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        detail.setCommande(commande);

        Detail_Commande saved = detailCommandeRepository.save(detail);
        return detailCommandeMappers.toDto(saved);
    }

    // ✅ Ajouter plusieurs détails commandes via DTO
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

    // ✅ Récupérer un détail commande par ID
    @Override
    public Detail_CommandeResponse recupererDetailCommandeParId(long id) {
        Detail_Commande detail = detailCommandeRepository.findById(id).orElse(null);
        return (detail != null) ? detailCommandeMappers.toDto(detail) : null;
    }

    // ✅ Récupérer tous les détails commandes
    @Override
    public List<Detail_CommandeResponse> selectAllDetailsCommande() {
        return detailCommandeRepository.findAll().stream()
                .map(detailCommandeMappers::toDto)
                .collect(Collectors.toList());
    }

    // ✅ Supprimer un détail commande par ID
    @Override
    public void deleteDetailCommandeById(long id) {
        detailCommandeRepository.deleteById(id);
    }

    // ✅ Supprimer tous les détails commandes
    @Override
    public void deleteAllDetailsCommande() {
        detailCommandeRepository.deleteAll();
    }

    // ✅ Compter les détails commandes
    @Override
    public long countDetailsCommande() {
        return detailCommandeRepository.count();
    }

    // ✅ Vérifier si un détail commande existe
    @Override
    public boolean verifDetailCommandeById(long id) {
        return detailCommandeRepository.existsById(id);
    }
}
