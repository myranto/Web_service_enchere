package varotra.web_service_enchere.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import varotra.web_service_enchere.FormatToJson.ToJsonData;
import varotra.web_service_enchere.Repository.ClientRepository;
import varotra.web_service_enchere.model.Client;
import varotra.web_service_enchere.model.security.Token;
import varotra.web_service_enchere.service.ClientService;
import varotra.web_service_enchere.service.TokenService;

@Service
public class ClientImplementService implements ClientService {
    private final ClientRepository rep;
    private final TokenService serv;

    public ClientImplementService(ClientRepository rep, TokenService serv) {
        this.rep = rep;
        this.serv = serv;
    }

    @Override
    public ResponseEntity<ToJsonData> login(Client cli){
//        String sql = "SELECT * FROM client where email='"+cli.getEmail()+"' and mdp='"+cli.getMdp()+"'";
        try {
            Client client = rep.findClientByEmailAndMdp(cli.getEmail(),cli.getMdp());
            if (client == null) {
                throw new Exception("id not found for email "+cli.getEmail());
            }
            Token tok = new Token();
            tok.setId(client.getId());
            serv.Create(client.getMdp(),client.getId());
            tok = serv.checkToken(client.getId());
            client.setToken(tok.getTok());
            ToJsonData<Client,Object> res = new ToJsonData<>(client,null);
            return new ResponseEntity<ToJsonData>(res, HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<ToJsonData>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ToJsonData> Logout(int id) throws Exception {
        ToJsonData<String,Object> check = new ToJsonData<>("log out reussi",null);
        serv.delete(id);
        return new ResponseEntity<ToJsonData>(check, HttpStatus.OK);
    }
}
