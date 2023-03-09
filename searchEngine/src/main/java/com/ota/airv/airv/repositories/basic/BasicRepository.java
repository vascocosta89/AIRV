package com.ota.airv.airv.repositories.basic;

import com.ota.airv.airv.model.basic.BasicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicRepository
        extends JpaRepository<BasicEntity,Long> {

    BasicEntity findByName(String name);
}
