package com.meli.challenge.dna.infrastructure.adapters.input.rest.api;

import com.meli.challenge.dna.application.dto.DNARequestDTO;
import com.meli.challenge.dna.application.dto.MutantCount;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/dna/")
public interface DNASequenceAPI {

    @PostMapping("mutant/")
    public ResponseEntity<Void> isMutant(@RequestBody DNARequestDTO dna);

    @GetMapping("stats/")
    public ResponseEntity<MutantCount> mutantStats();

}
