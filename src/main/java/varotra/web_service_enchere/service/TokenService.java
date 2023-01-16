package varotra.web_service_enchere.service;


import varotra.web_service_enchere.MyExecption.ExpirationException;
import varotra.web_service_enchere.MyExecption.RessourceNotFoundException;
import varotra.web_service_enchere.model.security.Token;

import java.security.NoSuchAlgorithmException;

public interface TokenService {
    public String generate(String mdp,int id) throws NoSuchAlgorithmException;
    public void delete(int idadmin) throws Exception;
    public Token checkToken(int ida) throws ExpirationException;
    public  void Create(String mdp,int id) throws Exception;
    public  void checkTokens(String tok,int id) throws RessourceNotFoundException, ExpirationException;
}
