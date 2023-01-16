package varotra.web_service_enchere.service.implement;

import org.springframework.stereotype.Service;
import varotra.web_service_enchere.MyExecption.ExpirationException;
import varotra.web_service_enchere.MyExecption.RessourceNotFoundException;
import varotra.web_service_enchere.Repository.TokenRepository;
import varotra.web_service_enchere.model.security.Token;
import varotra.web_service_enchere.service.TokenService;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

@Service
public class TokenImplementService implements TokenService {
    private TokenRepository rep = null;

    public TokenImplementService(TokenRepository rep) {
        this.rep = rep;
    }
    @Override
    public String generate(String mdp,int id) throws NoSuchAlgorithmException {
        Timestamp t = new Timestamp(System.currentTimeMillis());
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] mess = md.digest(String.valueOf(id).getBytes());
        BigInteger no = new BigInteger(1,mess);
        byte[] pwd = md.digest(String.valueOf(t).getBytes());
        BigInteger p = new BigInteger(1,pwd);
        String hashmdp = p.toString(16);
        String Hash = no.toString(16);
        while (Hash.length()<16)
        {
            Hash+="0";
        }
        while (hashmdp.length()<16){
            hashmdp+="1";
        }
        String value = hashmdp+Hash;
        return value;
    }
    @Override
    public Token checkToken(int ida) throws ExpirationException {
        Token tok = rep.findTokenByIdclientOrderByDateDesc(ida);
        if (tok==null)
            throw new ExpirationException("expiration");
        Timestamp cur = new Timestamp(System.currentTimeMillis());

        try {
            if ((cur.compareTo(tok.getDate())>0) || (tok.getDate()==null)){
                throw new ExpirationException("expiration");
            }
        }catch (Exception e){
//            throw new ExpirationException("expirerr")
            throw e;
        }
        return tok;
    }

    @Override
    public void Create(String mdp,int id) throws Exception {
        String token=  generate(mdp,id);
        Token t = new Token();
        t.setIdclient(id);
        t.setDate(new Timestamp(System.currentTimeMillis()));
        t.getDate().setMinutes(t.getDate().getMinutes()+15);
        t.setTok(token);
        rep.save(t);
    }

    @Override
    public void checkTokens(String tok,int id) throws RessourceNotFoundException, ExpirationException {
        try {
            Token t = checkToken(id);
            if (!tok.equals(t.getTok())){
                throw new RessourceNotFoundException("token is expired ","id",id);
            }
        } catch (ExpirationException e) {
            throw e;
        }catch (RessourceNotFoundException ex){
            throw ex;
        }
    }

    @Override
    public void delete(int idadmin) throws Exception {
        rep.deleteTokenByIdclient(idadmin);
    }
}
