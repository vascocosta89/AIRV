package com.ota.airv.airv.repositories;

import com.ota.airv.airv.model.BasicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicRepository
        extends JpaRepository<BasicEntity,Long> {

    BasicEntity findByName(String name);
}
