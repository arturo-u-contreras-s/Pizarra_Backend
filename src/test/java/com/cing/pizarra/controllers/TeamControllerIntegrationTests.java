package com.cing.pizarra.controllers;

import com.cing.pizarra.TestDataUtil;
import com.cing.pizarra.domain.entities.TeamEntity;
import com.cing.pizarra.services.TeamService;
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
public class TeamControllerIntegrationTests {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private TeamService teamService;

    @Autowired
    public TeamControllerIntegrationTests(MockMvc mockMvc, TeamService teamService) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.teamService = teamService;
    }

    @Test
    public void testThatCreateTeamSuccessfullyReturnsHttp201Created() throws Exception {
        TeamEntity testTeamA = TestDataUtil.createTestTeamEntityA();
        testTeamA.setTeamId(null);
        String teamJson = objectMapper.writeValueAsString(testTeamA);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teamJson) // http post requires JSON. need a JSON string -> objectmapper
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateTeamSuccessfullyReturnsSavedTeam() throws Exception {
        TeamEntity testTeamA = TestDataUtil.createTestTeamEntityA();
        testTeamA.setTeamId(null);
        String teamJson = objectMapper.writeValueAsString(testTeamA);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teamJson) // http post requires JSON. need a JSON string -> objectmapper
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.teamId").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.teamName").value("Primer Equipo")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.teamHeadCoach").value("Carlo Ancelotti")
        );
    }

    @Test
    public void testThatGetTeamsSuccessfullyReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetTeamsSuccessfullyReturnsListOfTeams() throws Exception {
        TeamEntity testTeamEntityA = TestDataUtil.createTestTeamEntityA();
        teamService.saveTeam(testTeamEntityA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect( // test what is returned
                MockMvcResultMatchers.jsonPath("$[0].teamId").value(1L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].teamName").value("Primer Equipo")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].teamHeadCoach").value("Carlo Ancelotti")
        );
    }

    @Test
    public void testThatGetTeamSuccessfullyReturnsHttpStatus200WhenTeamExists() throws Exception {
        TeamEntity testTeamEntityA = TestDataUtil.createTestTeamEntityA();
        teamService.saveTeam(testTeamEntityA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/teams/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetTeamSuccessfullyReturnsHttpStatus404WhenTeamDNE() throws Exception {
        TeamEntity testTeamEntityA = TestDataUtil.createTestTeamEntityA();
        teamService.saveTeam(testTeamEntityA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/teams/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatGetTeamSuccessfullyReturnsTeam() throws Exception {
        TeamEntity testTeamEntityA = TestDataUtil.createTestTeamEntityA();
        teamService.saveTeam(testTeamEntityA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/teams/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect( // test what is returned
                MockMvcResultMatchers.jsonPath("$.teamId").value(1L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.teamName").value("Primer Equipo")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.teamHeadCoach").value("Carlo Ancelotti")
        );
    }

    @Test
    public void testThatDeleteAuthorReturnsHttpStatus204ForNonExistingAuthor() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/teams/999")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }

    @Test
    public void testThatDeleteAuthorReturnsHttpStatus204ForExistingAuthor() throws Exception {
        TeamEntity testTeamEntityA = TestDataUtil.createTestTeamEntityA();
        teamService.saveTeam(testTeamEntityA);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/teams/" + testTeamEntityA.getTeamId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }
}
