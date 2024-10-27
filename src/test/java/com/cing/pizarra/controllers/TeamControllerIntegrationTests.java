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

import com.cing.pizarra.domain.entities.TeamEntity;
import com.cing.pizarra.services.TeamService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
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
    /* Test that createTeam() successfully returns HTTP 201 created */
    public void createTeamTest1() throws Exception {
        TeamEntity testTeamEntity = TeamEntity.builder().teamId(null).teamName("Primer Equipo").teamHeadCoach("Carlo Ancelotti").build();
        String teamJson = objectMapper.writeValueAsString(testTeamEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teamJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    /* Test that createTeam() successfully returns the created team */
    public void createTeamTest2() throws Exception {
        TeamEntity testTeamEntity = TeamEntity.builder().teamId(null).teamName("Primer Equipo").teamHeadCoach("Carlo Ancelotti").build();
        String teamJson = objectMapper.writeValueAsString(testTeamEntity);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teamJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.teamId").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.teamName").value("Primer Equipo")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.teamHeadCoach").value("Carlo Ancelotti")
        );
    }

    @Test
    /* Test that getAllTeams() successfully returns HTTP status 200 */
    public void getAllTeamsTest1() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk());
    }

    @Test
    /* Test that getAllTeams() successfully returns teams */
    public void getAllTeamsTest2() throws Exception {
        TeamEntity testTeamEntityA = teamService.saveTeam(TeamEntity.builder().teamId(null).teamName("Primer Equipo").teamHeadCoach("Carlo Ancelotti").build());
        TeamEntity testTeamEntityB = teamService.saveTeam(TeamEntity.builder().teamId(null).teamName("Castilla").teamHeadCoach("Raul Gonzalez").build());
        mockMvc.perform(
                MockMvcRequestBuilders.get("/teams")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].teamId").value(testTeamEntityA.getTeamId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].teamName").value("Primer Equipo")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].teamHeadCoach").value("Carlo Ancelotti")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].teamId").value(testTeamEntityB.getTeamId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].teamName").value("Castilla")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].teamHeadCoach").value("Raul Gonzalez")
        );
    }

    @Test
    /* Test that getTeamById() successfully returns HTTP status 200 */
    public void getTeamTest1() throws Exception {
        TeamEntity testTeamEntity = teamService.saveTeam(TeamEntity.builder().teamId(null).teamName("Primer Equipo").teamHeadCoach("Carlo Ancelotti").build());
        mockMvc.perform(
                MockMvcRequestBuilders.get("/teams/" + testTeamEntity.getTeamId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk());
    }

    @Test
    /* Test that getTeamById() successfully returns team */
    public void getTeamTest2() throws Exception {
        TeamEntity testTeamEntity = teamService.saveTeam(TeamEntity.builder().teamId(null).teamName("Primer Equipo").teamHeadCoach("Carlo Ancelotti").build());
        mockMvc.perform(
                MockMvcRequestBuilders.get("/teams/" + testTeamEntity.getTeamId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.teamId").value(testTeamEntity.getTeamId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.teamName").value("Primer Equipo")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.teamHeadCoach").value("Carlo Ancelotti")
        );
    }

    @Test
    /* Test that getTeamById() returns HTTP status 404 for a non-existing team */
    public void getTeamTest3() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/teams/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    /* Test that fullUpdateTeam() successfully returns HTTP status 200 */
    public void updateTeamTest1() throws Exception {
        TeamEntity testTeamEntityA = teamService.saveTeam(TeamEntity.builder().teamId(null).teamName("Primer Equipo").teamHeadCoach("Carlo Ancelotti").build());
        TeamEntity updatedTeam = TeamEntity.builder().teamId(testTeamEntityA.getTeamId()).teamName("Primer Equipo").teamHeadCoach("Zinedine Zidane").build();
        String teamJson = objectMapper.writeValueAsString(updatedTeam);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/teams/" + updatedTeam.getTeamId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teamJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    /* Test that fullUpdateTeam() successfully returns the updated Team */
    public void updateTeamTest2() throws Exception {
        TeamEntity testTeamEntityA = teamService.saveTeam(TeamEntity.builder().teamId(null).teamName("Primer Equipo").teamHeadCoach("Carlo Ancelotti").build());
        TeamEntity updatedTeam = TeamEntity.builder().teamId(testTeamEntityA.getTeamId()).teamName("Primer Equipo").teamHeadCoach("Zinedine Zidane").build();
        String teamJson = objectMapper.writeValueAsString(updatedTeam);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/teams/" + updatedTeam.getTeamId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teamJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.teamId").value(updatedTeam.getTeamId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.teamName").value(updatedTeam.getTeamName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.teamHeadCoach").value(updatedTeam.getTeamHeadCoach())
        );
    }


    @Test
    /* Test that fullUpdateTeam() returns HTTP status 404 for a non-existing team */
    public void updateTeamTest3() throws Exception {
        TeamEntity updatedTeam = TeamEntity.builder().teamId(99L).teamName("Primer Equipo").teamHeadCoach("Zinedine Zidane").build();
        String teamJson = objectMapper.writeValueAsString(updatedTeam);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/teams/" + updatedTeam.getTeamId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(teamJson)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    /* Test that delete team returns HTTP status 204 for an existing team */
    public void deleteTeamTest1() throws Exception {
        TeamEntity testTeamEntity = teamService.saveTeam(TeamEntity.builder().teamId(null).teamName("Primer Equipo").teamHeadCoach("Carlo Ancelotti").build());
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/teams/" + testTeamEntity.getTeamId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }

    @Test
    /* Test that delete team returns HTTP status 404 for a non-existing team */
    public void deleteTeamTest2() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/teams/999")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }
}