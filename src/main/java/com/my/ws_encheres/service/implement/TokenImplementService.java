package com.my.ws_encheres.service.implement;

import com.my.ws_encheres.MyExecption.ExpirationException;
import com.my.ws_encheres.MyExecption.RessourceNotFoundException;
import com.my.ws_encheres.Repository.TokenRepository;
import com.my.ws_encheres.model.security.Token;
import com.my.ws_encheres.service.TokenService;
import org.springframework.stereotype.Service;

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
        Token tok = rep.findTopByIdclientOrderByDateDesc(ida);
        if (tok==null)
            throw new ExpirationException("expiration 1:not connecter");
        Timestamp cur = new Timestamp(System.currentTimeMillis());

        try {
            if ((cur.compareTo(tok.getDate())>0) || (tok.getDate()==null)){
                throw new ExpirationException("expiration 2");
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
        t.getDate().setMinutes(t.getDate().getMinutes()+60);
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
