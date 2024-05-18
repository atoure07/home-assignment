package com.example.homeassignment.samples;

import com.example.homeassignment.domains.WinCombination;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class WinCombinationSamples {

    public static WinCombination oneWinCombination(int count, Double rewardMultiplier, List<List<String>> coveredAreas, String group) {

        return WinCombination.builder()
                .count(count)
                .reward_multiplier(rewardMultiplier)
                .covered_areas(coveredAreas)
                .group(group)
                .build();
    }
}
