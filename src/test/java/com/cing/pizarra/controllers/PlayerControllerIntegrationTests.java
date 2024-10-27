package com.cing.pizarra.controllers;

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

import com.cing.pizarra.TestDataUtil;
import com.cing.pizarra.domain.entities.PlayerEntity;
import com.cing.pizarra.domain.entities.TeamEntity;
import com.cing.pizarra.services.PlayerService;
import com.cing.pizarra.services.TeamService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class PlayerControllerIntegrationTests {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private PlayerService playerService;
    private TeamService teamService;

    @Autowired
    public PlayerControllerIntegrationTests(MockMvc mockMvc, PlayerService playerService, TeamService teamService) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @Test
    /* Test that createPlayer() successfully returns HTTP 201 created */
    public void createPlayerTest1() throws Exception {
        TeamEntity teamEntity = teamService.saveTeam(TeamEntity.builder().teamId(null).teamName("Primer Equipo").teamHeadCoach("Carlo Ancelotti").build());
        PlayerEntity playerEntity = TestDataUtil.createTestPlayerEntityA(teamEntity);
        String createPlayerJson = objectMapper.writeValueAsString(playerEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createPlayerJson)
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    /* Test that createPlayer() successfully returns created player */
    public void createPlayerTest2() throws Exception {
        TeamEntity teamEntity = teamService.saveTeam(TeamEntity.builder().teamId(null).teamName("Primer Equipo").teamHeadCoach("Carlo Ancelotti").build());
        PlayerEntity playerEntity = TestDataUtil.createTestPlayerEntityA(teamEntity);
        String createPlayerJson = objectMapper.writeValueAsString(playerEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createPlayerJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.firstName").value("Cristiano")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.kitNumber").value(7)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.position").value("Forward")
        );
    }

    @Test
    /* Test that getAllPlayers() successfully returns HTTP 200 */
    public void getAllPlayersTest1() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/players")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    /* Test that getAllPlayers() successfully returns players */
    public void getAllPlayersTest2() throws Exception {
        PlayerEntity testPlayerEntityA = playerService.savePlayer(TestDataUtil.createTestPlayerEntityB());
        PlayerEntity testPlayerEntityB = playerService.savePlayer(TestDataUtil.createTestPlayerEntityB());
        mockMvc.perform(
                MockMvcRequestBuilders.get("/players")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].playerId").value(testPlayerEntityA.getPlayerId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].lastName").value(testPlayerEntityA.getLastName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].kitNumber").value(testPlayerEntityA.getKitNumber())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].position").value(testPlayerEntityA.getPosition())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].playerId").value(testPlayerEntityB.getPlayerId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].lastName").value(testPlayerEntityB.getLastName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].kitNumber").value(testPlayerEntityB.getKitNumber())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].position").value(testPlayerEntityB.getPosition())
        );
    }

    @Test
    /* Test that getPlayerById successfully returns HTTP 200 */
    public void getPlayerTest1() throws Exception {
        PlayerEntity testPlayerEntity = playerService.savePlayer(TestDataUtil.createTestPlayerEntityB());
        mockMvc.perform(
                MockMvcRequestBuilders.get("/players/" + testPlayerEntity.getPlayerId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk());
    }

    @Test
    /* Test that getPlayerById successfully returns player */
    public void getPlayerTest2() throws Exception {
        PlayerEntity testPlayerEntity = playerService.savePlayer(TestDataUtil.createTestPlayerEntityB());
        mockMvc.perform(
                MockMvcRequestBuilders.get("/players/" + testPlayerEntity.getPlayerId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.playerId").value(testPlayerEntity.getPlayerId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.lastName").value(testPlayerEntity.getLastName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.kitNumber").value(testPlayerEntity.getKitNumber())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.position").value(testPlayerEntity.getPosition())
        );
    }

    @Test
    /* Test that getPlayerById returns HTTP 404 if player does not exist */
    public void getPlayerTest3() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/players/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    /* Test that fullPlayerUpdate successfully returns HTTP 200 and returns updated player */
    public void updatePlayerTest1() throws Exception {
        TeamEntity teamEntity = teamService.saveTeam(TeamEntity.builder().teamId(null).teamName("Primer Equipo").teamHeadCoach("Carlo Ancelotti").build());
        PlayerEntity savedPlayerEntity = playerService.savePlayer(TestDataUtil.createTestPlayerEntityA(teamEntity));

        PlayerEntity updatedPlayerEntity = TestDataUtil.createTestPlayerEntityA(teamEntity);
        updatedPlayerEntity.setPlayerId(savedPlayerEntity.getPlayerId());
        updatedPlayerEntity.setPosition("Midfielder");
        String createPlayerJson = objectMapper.writeValueAsString(updatedPlayerEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/players/" + updatedPlayerEntity.getPlayerId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createPlayerJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.firstName").value("Cristiano")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.kitNumber").value(7)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.position").value("Midfielder")
        );
    }

    @Test
    /* Test that delete player returns HTTP status 204 for an existing player */
    public void deletePlayerTest1() throws Exception {
        PlayerEntity testPlayerEntity = playerService.savePlayer(TestDataUtil.createTestPlayerEntityB());
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/players/" + testPlayerEntity.getPlayerId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }

    @Test
    /* Test that deleteById() returns HTTP status 404 for a non-existing player */
    public void deletePlayerTest2() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/players/999")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }
}