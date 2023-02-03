package com.my.ws_encheres.Repository;

import com.my.ws_encheres.model.enchere.Enchere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public interface EnchereRepository extends JpaRepository<Enchere,Integer> {
//        @Query("select c from Enchere c where c.status=0 and c.idclient.id!=:idclient order by c.id asc ")
//        public ArrayList<Enchere> findEnchereTsyClientMbolaTsyTapitara(@Param("idclient") int idclient);
        @Query("select c from Enchere c where c.status=0 order by c.id asc ")
        public ArrayList<Enchere> findEnchereNotFinish();
        @Query("select c from Enchere c where c.idclient.id=:idclient order by c.id asc")
        ArrayList<Enchere> findEncheresClient(@Param("idclient") int idclient);

        @Query("select c from Enchere c where c.status=1 and c.idclient.id=:idclient order by c.id asc ")
        ArrayList<Enchere> findEnchereByIdclientAndStatusEquals(int idclient);

        Enchere findTopById(int idenchere);
}
