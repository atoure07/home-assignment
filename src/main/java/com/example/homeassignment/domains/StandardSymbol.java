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
public class StandardSymbol {
    private final int column;
    private final int row;
    private final HashMap<String, Integer> symbols;
}
