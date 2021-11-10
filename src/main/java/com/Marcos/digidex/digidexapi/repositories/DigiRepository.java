package com.Marcos.digidex.digidexapi.repositories;

import com.Marcos.digidex.digidexapi.entities.Digimon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DigiRepository extends JpaRepository<Digimon, Long> {

    Optional<Digimon> findByName(String name);

}
