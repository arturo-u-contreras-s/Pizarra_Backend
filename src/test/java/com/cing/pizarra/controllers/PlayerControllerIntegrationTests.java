package com.cing.pizarra.controllers;

import com.cing.pizarra.TestDataUtil;
import com.cing.pizarra.domain.dto.PlayerDto;
import com.cing.pizarra.domain.entities.PlayerEntity;
import com.cing.pizarra.domain.entities.TeamEntity;
import com.cing.pizarra.services.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest // Integration test so we need a running spring application
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // Clean before each run
@AutoConfigureMockMvc // creates an instance of Mock Mvc for us and places it into our application context ready for use
public class PlayerControllerIntegrationTests {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private PlayerService playerService;

    @Autowired
    public PlayerControllerIntegrationTests(MockMvc mockMvc, PlayerService playerService) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.playerService = playerService;
    }

    @Test
    public void testThatCreatePlayerSuccessfullyReturnsHttp201Created() throws Exception {
        PlayerDto playerDtoA = TestDataUtil.createTestPlayerDtoA(null);
        String createPlayerJson = objectMapper.writeValueAsString(playerDtoA);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createPlayerJson) // http post requires JSON. need a JSON string -> objectmapper
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreatePlayerSuccessfullyReturnsCreatedBook() throws Exception {
        PlayerDto playerDtoA = TestDataUtil.createTestPlayerDtoA(null);
        String createPlayerJson = objectMapper.writeValueAsString(playerDtoA);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createPlayerJson) // http post requires JSON. need a JSON string -> objectmapper
        ).andExpect( // test what is returned
                MockMvcResultMatchers.jsonPath("$.playerName").value("Cristiano Ronaldo")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.kitNumber").value(7)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.position").value("Forward")
        );
    }

    @Test
    public void testThatGetPlayersSuccessfullyReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/players")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetTeamsSuccessfullyReturnsListOfTeams() throws Exception {
        PlayerEntity testPlayerEntityA = TestDataUtil.createTestPlayerEntityA(null);
        playerService.savePlayer(testPlayerEntityA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/players")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect( // test what is returned
                MockMvcResultMatchers.jsonPath("$[0].playerId").value(1L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].playerName").value("Cristiano Ronaldo")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].kitNumber").value(7)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].position").value("Forward")
        );
    }

    @Test
    public void testThatGetPlayerSuccessfullyReturnsHttpStatus200() throws Exception {
        PlayerEntity testPlayerEntityA = TestDataUtil.createTestPlayerEntityA(null);
        playerService.savePlayer(testPlayerEntityA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/players/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetPlayerDNEReturnsHttpStatus404() throws Exception {
        PlayerEntity testPlayerEntityA = TestDataUtil.createTestPlayerEntityA(null);
        playerService.savePlayer(testPlayerEntityA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/players/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatGetTeamSuccessfullyReturnsTeam() throws Exception {
        PlayerEntity testPlayerEntityA = TestDataUtil.createTestPlayerEntityA(null);
        playerService.savePlayer(testPlayerEntityA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/players/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect( // test what is returned
                MockMvcResultMatchers.jsonPath("$.playerId").value(1L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.playerName").value("Cristiano Ronaldo")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.kitNumber").value(7)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.position").value("Forward")
        );
    }

    @Test
    public void testThatGetPlayerDNEReturnsHttpStatus204() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/players/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatGetPlayerExistReturnsHttpStatus204() throws Exception {
        PlayerEntity testPlayerEntityA = TestDataUtil.createTestPlayerEntityA(null);
        playerService.savePlayer(testPlayerEntityA);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/players/" + testPlayerEntityA.getPlayerId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent());
    }
}
