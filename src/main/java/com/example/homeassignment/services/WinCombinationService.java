package com.example.homeassignment.services;

import com.example.homeassignment.domains.GameConfiguration;
import com.example.homeassignment.domains.Matrix;
import com.example.homeassignment.dto.MatrixResponseDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class WinCombinationService {
    public MatrixResponseDto winOrLoss(Matrix matrix, GameConfiguration gameConfiguration, double bettingAmount) {
        HashMap<String, List<String>> applied_winning_combinations = new HashMap<>();
        AtomicReference<Double> reward = new AtomicReference<>(bettingAmount);

        Set<String> generatedSymbolsKey = matrix.getGeneratedSymbols().keySet();
        generatedSymbolsKey.forEach(key -> {
            Integer count = matrix.getGeneratedSymbols().get(key);
            handleSameSymbolsWin(gameConfiguration, key, count, applied_winning_combinations, reward);
        });

        handleLinearSymbolsWin(matrix, gameConfiguration, applied_winning_combinations, reward);
        applyBonusRewardMultiplierOrExtra(matrix, gameConfiguration, applied_winning_combinations, reward);
        return getMatrixResponseDto(matrix, applied_winning_combinations, reward.get());
    }

    private static void handleLinearSymbolsWin(Matrix matrix, GameConfiguration gameConfiguration, HashMap<String, List<String>> applied_winning_combinations, AtomicReference<Double> reward) {
        Set<String> appliedWinningCombinationsKeys = applied_winning_combinations.keySet();
        appliedWinningCombinationsKeys.forEach(s -> {
            gameConfiguration.getWin_combinations().values()
                    .stream()
                    .filter(winCombination -> winCombination.getCovered_areas() != null)
                    .forEach(winCombination -> {
                        List<List<String>> coveredAreas = winCombination.getCovered_areas();
                        for (int i = 0; i < coveredAreas.size(); i++) {
                            Collection<String> similar = new HashSet<String>(matrix.getCovered_areas().get(s).getCovered_areas());
                            similar.retainAll(coveredAreas.get(i));
                            if (similar.size() == coveredAreas.get(i).size()) {
                                applied_winning_combinations.get(s).add(winCombination.getGroup());
                                reward.set(reward.get() * winCombination.getReward_multiplier());
                                return;
                            }
                        }
                    });
        });
    }

    private static MatrixResponseDto getMatrixResponseDto(Matrix matrix, HashMap<String, List<String>> applied_winning_combinations, Double reward) {
        return MatrixResponseDto.builder()
                .matrix(matrix.getMatrix())
                .applied_bonus_symbol(matrix.getBonusSymbol())
                .reward(reward)
                .applied_winning_combinations(applied_winning_combinations)
                .build();
    }

    private static void applyBonusRewardMultiplierOrExtra(Matrix matrix, GameConfiguration gameConfiguration,
                                                          HashMap<String, List<String>> applied_winning_combinations,
                                                          AtomicReference<Double> reward) {

        double rewardMultiplier = gameConfiguration.getSymbols().get(matrix.getBonusSymbol()).getReward_multiplier();
        if (rewardMultiplier > 0 && !applied_winning_combinations.isEmpty()) {
            reward.set(reward.get() * rewardMultiplier);
        } else if (!applied_winning_combinations.isEmpty()) {
            int extra = gameConfiguration.getSymbols().get(matrix.getBonusSymbol()).getExtra();
            reward.set(reward.get() + extra);
        } else {
            reward.set(0.0);
        }
    }

    private static void handleSameSymbolsWin(GameConfiguration gameConfiguration, String key, Integer count, HashMap<String, List<String>> applied_winning_combinations, AtomicReference<Double> reward) {
        gameConfiguration.getWin_combinations()
                .values()
                .stream()
                .filter(winCombination -> winCombination.getCount() == count)
                .forEach(winCombination -> {
                    if (applied_winning_combinations.containsKey(key)) {
                        applied_winning_combinations.get(key).add(winCombination.getGroup());
                    } else {
                        ArrayList<String> value = new ArrayList<>();
                        value.add(winCombination.getGroup());
                        applied_winning_combinations.put(key, value);
                    }
                    reward.set(reward.get() * winCombination.getReward_multiplier());
                    reward.set(reward.get() * gameConfiguration.getSymbols().get(key).getReward_multiplier());
                });
    }
}
