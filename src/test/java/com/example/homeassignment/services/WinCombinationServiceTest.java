package com.example.homeassignment.services;

import com.example.homeassignment.domains.CoveredArea;
import com.example.homeassignment.domains.GameConfiguration;
import com.example.homeassignment.domains.Matrix;
import com.example.homeassignment.dto.MatrixResponseDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.homeassignment.samples.GameConfigurationSamples.oneGameConfiguration;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WinCombinationServiceTest {
    private final WinCombinationService winCombinationService = new WinCombinationService();

    @Test
    void winOrLossWithSameSymbolsAndHorizontallyLinearSymbols() {
        GameConfiguration gameConfiguration = oneGameConfiguration();
        Matrix matrix = Matrix.builder()
                .bonusSymbol("x10")
                .build();

        matrix.getGeneratedSymbols().put("A", 3);
        CoveredArea coveredArea = new CoveredArea();
        coveredArea.setCovered_areas(List.of("0:0", "0:1", "0:2"));

        matrix.getCovered_areas().put("A", coveredArea);
        MatrixResponseDto matrixResponseDto = winCombinationService.winOrLoss(matrix, gameConfiguration, 100);

        assertEquals(matrixResponseDto.getApplied_bonus_symbol(), "x10");
        assertEquals(matrixResponseDto.getReward(), 60000.0);
        List<String> winningCombination = matrixResponseDto.getApplied_winning_combinations().get("A");

        assertEquals(winningCombination.get(0), "same_symbols");
        assertEquals(winningCombination.get(1), "horizontally_linear_symbols");
    }

    @Test
    void winOrLossWIthSameSymbolsAndVerticallyLinearSymbols() {
        GameConfiguration gameConfiguration = oneGameConfiguration();
        Matrix matrix = Matrix.builder()
                .bonusSymbol("MISS")
                .build();

        matrix.getGeneratedSymbols().put("B", 3);
        CoveredArea coveredArea = new CoveredArea();
        coveredArea.setCovered_areas(List.of("0:0", "1:0", "2:0"));

        matrix.getCovered_areas().put("B", coveredArea);
        MatrixResponseDto matrixResponseDto = winCombinationService.winOrLoss(matrix, gameConfiguration, 100);

        assertEquals(matrixResponseDto.getApplied_bonus_symbol(), "MISS");
        assertEquals(matrixResponseDto.getReward(), 10000.0);
        List<String> winningCombination = matrixResponseDto.getApplied_winning_combinations().get("B");

        assertEquals(winningCombination.get(0), "same_symbols");
        assertEquals(winningCombination.get(1), "vertically_linear_symbols");
    }

    @Test
    void winOrLossWithSameSymbolsAndLtrDiagonallyLinearSymbols() {
        GameConfiguration gameConfiguration = oneGameConfiguration();
        Matrix matrix = Matrix.builder()
                .bonusSymbol("x10")
                .build();

        matrix.getGeneratedSymbols().put("A", 3);
        CoveredArea coveredArea = new CoveredArea();
        coveredArea.setCovered_areas(List.of("0:0", "1:1", "2:2"));

        matrix.getCovered_areas().put("A", coveredArea);
        MatrixResponseDto matrixResponseDto = winCombinationService.winOrLoss(matrix, gameConfiguration, 100);

        assertEquals(matrixResponseDto.getApplied_bonus_symbol(), "x10");
        assertEquals(matrixResponseDto.getReward(), 150000.0);
        List<String> winningCombination = matrixResponseDto.getApplied_winning_combinations().get("A");

        assertEquals(winningCombination.get(0), "same_symbols");
        assertEquals(winningCombination.get(1), "ltr_diagonally_linear_symbols");
    }

    @Test
    void winOrLossWithAndRtlDiagonallyLinearSymbols() {
        GameConfiguration gameConfiguration = oneGameConfiguration();
        Matrix matrix = Matrix.builder()
                .bonusSymbol("+500")
                .build();

        matrix.getGeneratedSymbols().put("C", 3);
        CoveredArea coveredArea = new CoveredArea();
        coveredArea.setCovered_areas(List.of("0:2", "1:1", "2:0"));

        matrix.getCovered_areas().put("C", coveredArea);
        MatrixResponseDto matrixResponseDto = winCombinationService.winOrLoss(matrix, gameConfiguration, 100);

        assertEquals(matrixResponseDto.getApplied_bonus_symbol(), "+500");
        assertEquals(matrixResponseDto.getReward(), 10500.0);
        List<String> winningCombination = matrixResponseDto.getApplied_winning_combinations().get("C");

        assertEquals(winningCombination.get(0), "same_symbols");
        assertEquals(winningCombination.get(1), "rtl_diagonally_linear_symbols");
    }
}