package varotra.web_service_enchere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import varotra.web_service_enchere.FormatToJson.ToJsonData;
import varotra.web_service_enchere.model.Client;
import varotra.web_service_enchere.service.ClientService;
import varotra.web_service_enchere.service.TokenService;

@RestController
@RequestMapping("/enchere/cli")
@CrossOrigin(methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.OPTIONS})
public class ClientController {
    private final ClientService serve;
    private final TokenService tok_service;

    public ClientController(ClientService serve, TokenService tok_service) {
        this.serve = serve;
        this.tok_service = tok_service;
    }
    @GetMapping("/checkToken/{id}/{token}")
    public ResponseEntity<ToJsonData> isConnected(@PathVariable("id") int id,@PathVariable("token") String token){
        try {
            tok_service.checkTokens(token,id);
            return new ResponseEntity<>(new ToJsonData<>("is connected true",null),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<ToJsonData> login(@RequestBody Client cli) {
        return serve.login(cli);
    }
    @DeleteMapping("/log_out/{id}")
    public ResponseEntity<ToJsonData>LogOut(@PathVariable("id") int id){
        try {
            return serve.Logout(id);
        } catch (Exception e) {
            return new ResponseEntity<>(new ToJsonData(null,e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

}
