package com.example.homeassignment.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class SymbolAttribute {
    private final double reward_multiplier;
    private final int extra;
    private final String type;
    private final String impact;
}
