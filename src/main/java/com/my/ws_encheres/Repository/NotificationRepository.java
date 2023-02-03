package com.my.ws_encheres.Repository;

import com.my.ws_encheres.model.notif.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {
    public List<Notification> findAllByIdclientOrderByIdAsc(int idclient);
}
