package com.my.ws_encheres.Repository;

import com.my.ws_encheres.model.enchere.Duration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DurationRepository extends JpaRepository<Duration,Integer> {
    @Query("select c from Duration c order by c.id desc limit 1")
    public Duration findDurationByIdDesc();
}
