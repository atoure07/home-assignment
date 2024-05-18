package com.example.homeassignment.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CoveredArea {
    private List<String> covered_areas = new ArrayList<>();

    public CoveredArea addCoveredArea(String covered_area) {
        covered_areas.add(covered_area);
        return this;
    }
}
