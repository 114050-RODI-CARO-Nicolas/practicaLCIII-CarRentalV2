package com.example.rentacar.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 =
            new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    @Autowired
    private MockMvc mockMvc;


    @Test
    void getAllCarsTest() throws Exception {
        this.mockMvc.perform(get("/car")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));

    }

    /*

    Si tuviese un endpoint get por Id, el test correspondiente tendría esta estructura

     @Test
    void putGameResultTest() throws Exception {
        this.mockMvc.perform(put("/games/1/result?local_goals=3&visitor_goals=0"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.game.local.id").value(1))
                .andExpect(jsonPath("$.game.local.name").value("Argentina"))
                .andExpect(jsonPath("$.game.visitor.id").value(5))
                .andExpect(jsonPath("$.game.visitor.name").value("Canada"))
                .andExpect(jsonPath("$.game.stadium").value("Mercedes Benz Stadium - Atlanta, GA"))
                .andExpect(jsonPath("$.game.date").value("20-06-2024 20:00:00"))
                .andExpect(jsonPath("$.game.fase_game").value("GROUP_STAGE"))
                .andExpect(jsonPath("$.result").value("LOCAL_WIN"))
                .andExpect(jsonPath("$.local_goals").value(3))
                .andExpect(jsonPath("$.visitor_goals").value(0));;
    }

     */

    @Test
    void registerCarTest() throws Exception
    {
        mockMvc.perform(post("/car")
                .param("carTypeId", "1")
                .param("brand", "Toyota")
                .param("model", "Corolla"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand").value("Toyota"))
                .andExpect(jsonPath("$.model").value("Corolla"));
    }

    //--Si fuese un post con path variables en vez de request params,
    /*
        mockMvc.perform(post("/api/cars/carType/1/brand/Toyota/model/Corolla"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.brand").value("Toyota"))
                .andExpect(jsonPath("$.model").value("Corolla"));
    }
     */

    /* --Si fuese un post con request body en vez de parametros o path variables

       CarDTO carDTO = new CarDTO();
        carDTO.setCarTypeId(1L);
        carDTO.setBrand("Toyota");
        carDTO.setModel("Corolla");

         mockMvc.perform(post("/api/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(carDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.brand").value("Toyota"))
                .andExpect(jsonPath("$.model").value("Corolla"));
    }

    Ó
    emulando el contenido del string de json:

     String content = "{\"local_goals\":3,\"visitor_goals\":0}";

     */





}