package com.example.homeassignment.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class Probabilitie {
    private final List<StandardSymbol> standard_symbols;
    private final BonusSymbol bonus_symbols;

}
