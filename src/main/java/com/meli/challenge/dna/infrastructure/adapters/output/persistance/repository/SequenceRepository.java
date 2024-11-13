package com.meli.challenge.dna.infrastructure.adapters.output.persistance.repository;

import com.meli.challenge.dna.domain.model.Sequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceRepository extends JpaRepository<Sequence, Long> {
    long countByIsMutant(boolean isMutant);
}
