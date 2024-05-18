package com.example.homeassignment.samples;

import com.example.homeassignment.domains.GameConfiguration;
import com.example.homeassignment.domains.SymbolAttribute;
import com.example.homeassignment.domains.WinCombination;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

import static com.example.homeassignment.samples.ProbabilitieSamples.oneProbabilitieSamples;
import static com.example.homeassignment.samples.WinCombinationSamples.oneWinCombination;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class GameConfigurationSamples {

    public static GameConfiguration oneGameConfiguration() {
        final HashMap<String, SymbolAttribute> symbols = getSymbols();

        final HashMap<String, WinCombination> wincombinations = new HashMap<>();
        WinCombination combination1 = oneWinCombination(3, 1.0, null, "same_symbols");
        WinCombination combination2 = oneWinCombination(0, 2.0, List.of(List.of("0:0", "0:1", "0:2")), "horizontally_linear_symbols");
        WinCombination combination3 = oneWinCombination(0, 2.0, List.of(List.of("0:0", "1:0", "2:0")), "vertically_linear_symbols");
        WinCombination combination4 = oneWinCombination(0, 5.0, List.of(List.of("0:0", "1:1", "2:2")), "ltr_diagonally_linear_symbols");
        WinCombination combination5 = oneWinCombination(0, 5.0, List.of(List.of("0:2", "1:1", "2:0")), "rtl_diagonally_linear_symbols");

        wincombinations.put("same_symbol_3_times", combination1);
        wincombinations.put("same_symbols_horizontally", combination2);
        wincombinations.put("same_symbols_vertically", combination3);
        wincombinations.put("same_symbols_diagonally_left_to_right", combination4);
        wincombinations.put("same_symbols_diagonally_right_to_left", combination5);
        return GameConfiguration.builder()
                .columns(3)
                .rows(3)
                .symbols(symbols)
                .probabilities(oneProbabilitieSamples())
                .win_combinations(wincombinations)
                .build();
    }

    private static HashMap<String, SymbolAttribute> getSymbols() {
        final HashMap<String, SymbolAttribute> symbols = new HashMap<>();
        symbols.put("A", SymbolAttribute.builder()
                .reward_multiplier(30)
                .type("standard")
                .build());
        symbols.put("B", SymbolAttribute.builder()
                .reward_multiplier(50)
                .type("standard")
                .build());
        symbols.put("C", SymbolAttribute.builder()
                .reward_multiplier(20)
                .type("standard")
                .build());
        symbols.put("x10", SymbolAttribute.builder()
                .reward_multiplier(10)
                .type("bonus")
                .build());
        symbols.put("+500", SymbolAttribute.builder()
                .extra(500)
                .type("bonus")
                .build());
        symbols.put("MISS", SymbolAttribute.builder()
                .reward_multiplier(0)
                .type("bonus")
                .build());
        return symbols;
    }
}
