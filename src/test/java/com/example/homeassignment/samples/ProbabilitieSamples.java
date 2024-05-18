package com.example.homeassignment.samples;

import com.example.homeassignment.domains.Probabilitie;
import com.example.homeassignment.domains.StandardSymbol;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.example.homeassignment.samples.BonusSymbolSamples.oneBonusSymbolSamples;
import static com.example.homeassignment.samples.StandardSymbolSamples.oneStandardSymbol;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ProbabilitieSamples {

    public static Probabilitie oneProbabilitieSamples() {
        List<StandardSymbol> standardSymbols = List.of(oneStandardSymbol(0,0), oneStandardSymbol(0,1), oneStandardSymbol(0,2),
                oneStandardSymbol(1,0), oneStandardSymbol(1,1), oneStandardSymbol(1,2),
                oneStandardSymbol(2,0), oneStandardSymbol(2,1), oneStandardSymbol(2,2));

        return Probabilitie.builder()
                .bonus_symbols(oneBonusSymbolSamples())
                .standard_symbols(standardSymbols)
                .build();
    }
}
