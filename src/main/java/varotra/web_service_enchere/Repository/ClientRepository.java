package varotra.web_service_enchere.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import varotra.web_service_enchere.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    public Client findClientByEmailAndMdp(String email,String mdp);
}
