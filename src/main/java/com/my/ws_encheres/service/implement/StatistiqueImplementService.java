package com.my.ws_encheres.service.implement;

import com.my.ws_encheres.FormatToJson.ToJsonData;
import com.my.ws_encheres.Repository.StatistiqueRepository;
import com.my.ws_encheres.service.StatistiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StatistiqueImplementService implements StatistiqueService {
    private final StatistiqueRepository rep;

    public StatistiqueImplementService(StatistiqueRepository rep) {
        this.rep = rep;
    }

    @Override
    public ResponseEntity<ToJsonData> getStat() {
        return new ResponseEntity<>(new ToJsonData<>(rep.getStatistique(),null), HttpStatus.OK);
    }
}
