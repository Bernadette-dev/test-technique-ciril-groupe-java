package com.forest.simulation.config;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigLoader {

    private final ObjectMapper objectMapper;

    public ConfigLoader() {
        this.objectMapper = new ObjectMapper();
    }

    public SimulationConfig load(String filePath) throws IOException {
        return objectMapper.readValue(new File(filePath), SimulationConfig.class);
    }
}
