package com.example.homeassignment.utils;

import com.example.homeassignment.domains.CoveredArea;
import com.example.homeassignment.domains.GameConfiguration;
import com.example.homeassignment.domains.Matrix;
import com.example.homeassignment.domains.Probabilitie;
import com.example.homeassignment.domains.StandardSymbol;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@NoArgsConstructor(access = PRIVATE)
public class MatrixGeneratorUtils {

    private static int columns;
    private static int rows;

    public static Matrix generateMatrix(GameConfiguration gameConfiguration) {
        columns = gameConfiguration.getColumns();
        rows = gameConfiguration.getRows();

        Probabilitie probabilities = gameConfiguration.getProbabilities();
        List<StandardSymbol> standardSymbols = probabilities
                .getStandard_symbols();
        int size = standardSymbols == null ? 0 : standardSymbols.size();

        if (columns * rows != size) {
            log.error("Invalid Configuration. Columns and rows do not match with standard probabilities");
            throw new IllegalArgumentException();
        }

        return generateRandomSymbols(columns, rows, probabilities);
    }

    private static Matrix generateRandomSymbols(int columns, int rows, Probabilitie probabilitie) {
        String[][] strings = new String[columns][rows];
        Matrix matrix = Matrix.builder()
                .matrix(strings)
                .build();
        boolean bonusSymbolNotExists = true;

        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                if (bonusSymbolNotExists) {
                    Random rn = new Random();
                    int result = rn.nextInt(2) + 1;
                    if (result == 1) {
                        bonusSymbolNotExists = generateBonusSymbolIfNotAlreadyExists(matrix, probabilitie, column, row);
                        continue;
                    }
                }
                generateStandardSymbol(matrix, probabilitie, column, row, column, row);
            }
        }
        return matrix;
    }

    private static boolean generateBonusSymbolIfNotAlreadyExists(Matrix matrix, Probabilitie probabilitie, int column, int row) {
        WeightedRandomUtils<String> bonusSymbols = new WeightedRandomUtils<>();
        probabilitie.getBonus_symbols().getSymbols().forEach(bonusSymbols::addEntry);
        String bonusSymbolsRandom = bonusSymbols.getRandom();
        matrix.setBonusSymbol(bonusSymbolsRandom);
        matrix.getMatrix()[column][row] = bonusSymbolsRandom;
        return false;
    }

    private static void generateStandardSymbol(Matrix matrix, Probabilitie probabilitie, int finalColumn, int finalRow, int column, int row) {
        WeightedRandomUtils<String> standardSymbols = new WeightedRandomUtils<>();
        StandardSymbol standardSymbol = probabilitie.getStandard_symbols().stream()
                .filter(symbol -> symbol.getColumn() == finalColumn && symbol.getRow() == finalRow)
                .findFirst()
                .get();
        standardSymbol
                .getSymbols()
                .forEach(standardSymbols::addEntry);
        String standardSymbolsRandom = standardSymbols.getRandom();
        HashMap<String, Integer> generatedSymbols = matrix.getGeneratedSymbols();
        generatedSymbols.merge(standardSymbolsRandom, 1, Integer::sum);

        HashMap<String, CoveredArea> coveredAreas = matrix.getCovered_areas();
        String coveredArea = column + ":" + row;
        if (coveredAreas.containsKey(standardSymbolsRandom)) {
            coveredAreas.get(standardSymbolsRandom).addCoveredArea(coveredArea);
        } else {
            coveredAreas.put(standardSymbolsRandom, new CoveredArea().addCoveredArea(coveredArea));
        }

        matrix.getMatrix()[column][row] = standardSymbolsRandom;
    }

}
