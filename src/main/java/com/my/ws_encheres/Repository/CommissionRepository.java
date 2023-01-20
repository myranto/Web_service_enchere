package com.my.ws_encheres.Repository;

import com.my.ws_encheres.model.commission.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepository extends JpaRepository<Commission,Integer> {
    @Query("select c from Commission  c order by c.id desc  limit 1")
    public Commission getLastCommission();
}
