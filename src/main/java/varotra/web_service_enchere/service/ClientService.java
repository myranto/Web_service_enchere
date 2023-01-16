package varotra.web_service_enchere.service;

import org.springframework.http.ResponseEntity;
import varotra.web_service_enchere.FormatToJson.ToJsonData;
import varotra.web_service_enchere.model.Client;

public interface ClientService {
    public ResponseEntity<ToJsonData> login(Client cli);
    public ResponseEntity<ToJsonData> Logout(int id)throws Exception;
}
