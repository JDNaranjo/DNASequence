package com.meli.challenge.dna.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "sequences")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sequence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "sequence", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DNA> dna;

    @Column
    private boolean isMutant;
}
