package com.Marcos.digidex.digidexapi.repositories;

import com.Marcos.digidex.digidexapi.entities.Digimon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DigiRepository extends JpaRepository<Digimon, Long> {



}
