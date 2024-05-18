package com.example.homeassignment;

import com.example.homeassignment.domains.GameConfiguration;
import com.example.homeassignment.domains.Matrix;
import com.example.homeassignment.dto.MatrixResponseDto;
import com.example.homeassignment.services.WinCombinationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.example.homeassignment.utils.MatrixGeneratorUtils.generateMatrix;

public class Application {

    public static void main(String[] args) throws IOException {
        CommandLine cmd = getCommandLine(args);

        String inputFilePath = cmd.getOptionValue("config");
        String bettingAmount = cmd.getOptionValue("betting-amount");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        byte[] config = Files.readAllBytes(Paths.get(inputFilePath));
        GameConfiguration gameConfiguration = objectMapper.readValue(config, GameConfiguration.class);

        Matrix matrix = generateMatrix(gameConfiguration);
        WinCombinationService winCombinationService = new WinCombinationService();

        MatrixResponseDto matrixResponseDto = winCombinationService.winOrLoss(matrix, gameConfiguration, Double.parseDouble(bettingAmount));
        String matrixResponseString = objectMapper.writeValueAsString(matrixResponseDto);

        System.out.println(matrixResponseString);
    }

    private static CommandLine getCommandLine(String[] args) {
        Options options = new Options();

        Option input = new Option("c", "config", true, "input config file path");
        input.setRequired(true);
        options.addOption(input);

        Option output = new Option("b", "betting-amount", true, "betting amount to play");
        output.setRequired(true);
        options.addOption(output);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }
        return cmd;
    }

}
