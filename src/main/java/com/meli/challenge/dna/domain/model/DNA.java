package com.meli.challenge.dna.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dna")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DNA {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String dna;

    @ManyToOne
    @JoinColumn(name = "sequence_id")
    private Sequence sequence;
}
