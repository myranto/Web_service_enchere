package varotra.web_service_enchere.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import varotra.web_service_enchere.model.security.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token,Integer> {
    public void deleteTokenByIdclient(int idclient);
    public Token findTokenByIdclientOrderByDateDesc(int idclient);
}
