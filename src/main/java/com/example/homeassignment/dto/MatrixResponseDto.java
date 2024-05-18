package com.example.homeassignment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.HashMap;
import java.util.List;

@Getter
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatrixResponseDto {
    private String[][] matrix;
    private HashMap<String, List<String>> applied_winning_combinations;
    private String applied_bonus_symbol;
    @Setter
    private double reward;
}
