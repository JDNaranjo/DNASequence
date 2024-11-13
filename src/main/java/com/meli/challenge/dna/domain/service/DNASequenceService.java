package com.meli.challenge.dna.domain.service;

import com.meli.challenge.dna.application.dto.MutantCount;
import com.meli.challenge.dna.domain.model.DNA;
import com.meli.challenge.dna.domain.model.Sequence;
import com.meli.challenge.dna.domain.util.DNASequenceUtil;
import com.meli.challenge.dna.infrastructure.adapters.output.persistance.repository.DNARepository;
import com.meli.challenge.dna.infrastructure.adapters.output.persistance.repository.SequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.IntStream;

@Service
public class DNASequenceService {

    @Autowired
    private SequenceRepository sequenceRepository;

    @Autowired
    private DNARepository dnaRepository;

    public int isMutant(String[] dna) {
        if (IntStream.range(0, dna.length-1)
                .anyMatch(i -> IntStream.range(0, dna[i].length()-1)
                        .anyMatch(j -> checkHorizontally(dna, i, j) ||
                                checkVertically(dna, i, j) ||
                                checkDiagonal(dna, i, j) ||
                                checkAntiDiagonal(dna, i, j))
                )){

            saveSequence(dna, true);
            return 200;
        } else {
            saveSequence(dna, false);
            return 403;
        }
    }

    public MutantCount getStats() {
        long mutantCount = sequenceRepository.countByIsMutant(true);
        return new MutantCount(
                mutantCount,
                sequenceRepository.countByIsMutant(false),
                (double) mutantCount / sequenceRepository.count()
        );
    }

    private boolean findSequence(String dna){
        return DNASequenceUtil.dnaSequences.stream()
                .anyMatch(dna::contains);
    }

    private String buildString(char firstChar, char secondChar, char thirdChar, char fourthChar){
        return ""+firstChar+secondChar+thirdChar+fourthChar;
    }

    private boolean checkHorizontally(String[] dna, int i, int j) {
        return j + 3 < dna[i].length() && findSequence(
                buildString(dna[i].charAt(j),
                        dna[i].charAt(j + 1),
                        dna[i].charAt(j + 2),
                        dna[i].charAt(j + 3))
        );
    }

    private boolean checkVertically(String[] dna, int i, int j) {
        return i + 3 < dna.length && j < dna[i].length() &&
                j < dna[i + 1].length() && j < dna[i + 2].length() &&
                j < dna[i + 3].length() &&
                findSequence(
                        buildString(dna[i].charAt(j),
                                dna[i + 1].charAt(j),
                                dna[i + 2].charAt(j),
                                dna[i + 3].charAt(j))
                );
    }

    private boolean checkDiagonal(String[] dna, int i, int j) {
        return i + 3 < dna.length && j + 3 < dna[i].length() &&
                j + 1 < dna[i + 1].length() && j + 2 < dna[i + 2].length() &&
                j + 3 < dna[i + 3].length() &&
                findSequence(
                        buildString(dna[i].charAt(j),
                                dna[i + 1].charAt(j + 1),
                                dna[i + 2].charAt(j + 2),
                                dna[i + 3].charAt(j + 3))
                );
    }

    private boolean checkAntiDiagonal(String[] dna, int i, int j) {
        return i + 3 < dna.length && j - 3 >= 0 &&
                j - 2 >= 0 && j - 1 >= 0 && j >= 0 &&
                j < dna[i].length() && j - 1 < dna[i + 1].length() &&
                j - 2 < dna[i + 2].length() && j - 3 < dna[i + 3].length() &&
                findSequence(
                        buildString(dna[i].charAt(j),
                                dna[i + 1].charAt(j - 1),
                                dna[i + 2].charAt(j - 2),
                                dna[i + 3].charAt(j - 3))
                );
    }
    
    private void saveSequence(String[] dna, boolean isMutant){
        Sequence sequence = sequenceRepository.save(Sequence.builder()
                .isMutant(isMutant)
                .build());
        dnaRepository.saveAll(Arrays.stream(dna)
                .map(dnaString -> DNA.builder()
                        .dna(dnaString)
                        .sequence(sequence)
                        .build())
                .toList());
    }
}