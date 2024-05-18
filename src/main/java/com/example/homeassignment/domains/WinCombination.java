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
public class WinCombination {
    private final Double reward_multiplier;
    private final int count;
    private final String when;
    private final String group;
    private final List<List<String>> covered_areas;
}
