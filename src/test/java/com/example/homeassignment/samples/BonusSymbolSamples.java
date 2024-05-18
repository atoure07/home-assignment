package com.example.homeassignment.samples;

import com.example.homeassignment.domains.BonusSymbol;
import lombok.NoArgsConstructor;

import java.util.HashMap;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class BonusSymbolSamples {

    public static BonusSymbol oneBonusSymbolSamples() {
        final HashMap<String, Integer> symbols = getSymbols();
        return BonusSymbol.builder()
                .symbols(symbols)
                .build();
    }

    private static HashMap<String, Integer> getSymbols() {
        final HashMap<String, Integer> symbols = new HashMap<>();
        symbols.put("x10", 1);
        symbols.put("+500", 2);
        symbols.put("MISS", 3);
        return symbols;
    }

}
