package tn.esprit.spring.tpcafe_talbiranine.services.Commande;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafe_talbiranine.dto.Commande.CommandeRequest;
import tn.esprit.spring.tpcafe_talbiranine.dto.Commande.CommandeResponse;
import tn.esprit.spring.tpcafe_talbiranine.entites.Commande;
import tn.esprit.spring.tpcafe_talbiranine.mapper.Commande.CommandeMappers;
import tn.esprit.spring.tpcafe_talbiranine.repositories.CommandeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommandeService implements ICommandeServices {

    private final CommandeRepository commandeRepository;
    private final CommandeMappers commandeMapper;

    // ✅ Ajouter une commande via DTO
    @Override
    public CommandeResponse addCommande(CommandeRequest commandeRequest) {
        Commande commande = commandeMapper.toEntity(commandeRequest);
        Commande saved = commandeRepository.save(commande);
        return commandeMapper.toDto(saved);
    }

    // ✅ Ajouter plusieurs commandes via DTO
    @Override
    public List<CommandeResponse> saveCommandes(List<CommandeRequest> commandesRequest) {
        List<Commande> commandes = commandesRequest.stream()
                .map(commandeMapper::toEntity)
                .collect(Collectors.toList());
        List<Commande> saved = commandeRepository.saveAll(commandes);
        return saved.stream()
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
    }

    // ✅ Récupérer une commande par ID
    @Override
    public CommandeResponse selectCommandeById(long id) {
        return commandeRepository.findById(id)
                .map(commandeMapper::toDto)
                .orElse(null);
    }

    // ✅ Récupérer toutes les commandes
    @Override
    public List<CommandeResponse> selectAllCommandes() {
        return commandeRepository.findAll().stream()
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
    }

    // ✅ Supprimer une commande par ID
    @Override
    public void deleteCommandeById(long id) {
        commandeRepository.deleteById(id);
    }

    // ✅ Supprimer toutes les commandes
    @Override
    public void deleteAllCommandes() {
        commandeRepository.deleteAll();
    }

    // ✅ Compter les commandes
    @Override
    public long countCommandes() {
        return commandeRepository.count();
    }

    // ✅ Vérifier si une commande existe
    @Override
    public boolean verifCommandeById(long id) {
        return commandeRepository.existsById(id);
    }
}
