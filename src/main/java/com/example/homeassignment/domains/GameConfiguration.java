package com.example.homeassignment.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.HashMap;

@Getter
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameConfiguration {
    private final HashMap<String, SymbolAttribute> symbols;
    private final int columns;
    private final int rows;
    private final Probabilitie probabilities;
    private final HashMap<String, WinCombination> win_combinations;


}
