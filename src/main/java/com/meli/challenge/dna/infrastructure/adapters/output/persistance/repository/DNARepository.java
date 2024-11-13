package com.meli.challenge.dna.infrastructure.adapters.output.persistance.repository;

import com.meli.challenge.dna.domain.model.DNA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DNARepository extends JpaRepository<DNA, Long>{
}
