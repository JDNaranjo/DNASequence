package com.meli.challenge.dna.infrastructure.adapters.input.rest;

import com.meli.challenge.dna.application.dto.DNARequestDTO;
import com.meli.challenge.dna.application.dto.MutantCount;
import com.meli.challenge.dna.infrastructure.adapters.input.rest.api.DNASequenceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.meli.challenge.dna.domain.service.DNASequenceService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("api/dna")
public class DNASequenceController implements DNASequenceAPI {

    @Autowired
    private DNASequenceService dnaSequenceService;

    @Override
    public ResponseEntity<Void> isMutant(DNARequestDTO dna) {
        return new ResponseEntity<>(HttpStatusCode.valueOf(dnaSequenceService.isMutant(dna.getDna())));
    }

    @Override
    public ResponseEntity<MutantCount> mutantStats() {
        return new ResponseEntity<MutantCount>(dnaSequenceService.getStats(), HttpStatus.OK);
    }

}
