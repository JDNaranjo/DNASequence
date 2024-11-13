package com.meli.challenge.dna.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MutantCount(@JsonProperty("count_mutant_dna") long mutantAmount,
                          @JsonProperty("count_human_dna") long humanAmount,
                          double ratio) {
}
