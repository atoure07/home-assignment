package com.example.homeassignment.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.HashMap;

@Getter
@Setter
@Builder(toBuilder = true)
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class Matrix {
    private final HashMap<String, Integer> generatedSymbols = new HashMap<>();
    private final HashMap<String, CoveredArea> covered_areas = new HashMap<>();
    private final String[][] matrix;
    private String bonusSymbol;
}
