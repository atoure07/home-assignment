package com.example.homeassignment.samples;

import com.example.homeassignment.domains.StandardSymbol;
import lombok.NoArgsConstructor;

import java.util.HashMap;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class StandardSymbolSamples {

    public static StandardSymbol oneStandardSymbol(int column, int row) {
        final HashMap<String, Integer> symbols = getSymbols();
        return StandardSymbol.builder()
                .column(column)
                .row(row)
                .symbols(symbols)
                .build();
    }

    private static HashMap<String, Integer> getSymbols() {
        final HashMap<String, Integer> symbols = new HashMap<>();
        symbols.put("A", 1);
        symbols.put("C", 2);
        symbols.put("B", 3);
        return symbols;
    }
}
