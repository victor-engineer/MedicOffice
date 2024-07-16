package com.medical.office.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medical.office.entity.Medic;
import com.medical.office.service.MedicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MedicController.class)
public class MedicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicService medicService;

    // Inicialização manual do ObjectMapper
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper(); // Inicialização correta do ObjectMapper
    }

    @Test
    void testGetMedicAll() throws Exception {
        Medic medic1 = new Medic();
        medic1.setId(2L);
        medic1.setName("Dr. Luan Barbosa");
        medic1.setSpecialty("Cardiologia");

        Medic medic2 = new Medic();
        medic2.setId(3L);
        medic2.setName("Dr. Victor Lima");
        medic2.setSpecialty("Pediatria");

        given(medicService.findAll()).willReturn(Arrays.asList(medic1, medic2));

        mockMvc.perform(get("/medics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Dr. Luan Barbosa"))
                .andExpect(jsonPath("$[1].name").value("Dr. Victor Lima"));
    }

    @Test
    void testGetMedicById() throws Exception {
        Medic medic = new Medic();
        medic.setId(2L);
        medic.setName("Dr. Luan Barbosa");
        medic.setSpecialty("Cardiologia");

        given(medicService.findById(anyLong())).willReturn(Optional.of(medic));

        mockMvc.perform(get("/medics/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dr. Luan Barbosa"));
    }

    @Test
    void testCreateMedic() throws Exception {
        Medic medic = new Medic();
        medic.setId(1L);
        medic.setName("Dr. Luan Barbosa");
        medic.setSpecialty("Cardiologia");

        given(medicService.save(any(Medic.class))).willReturn(medic);

        mockMvc.perform(post("/medics")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(medic)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Dr. Luan Barbosa"));
    }

    @Test
    void testUpdateMedic() throws Exception {
        Medic medic = new Medic();
        medic.setId(1L);
        medic.setName("Dr. Luan Barbosa");
        medic.setSpecialty("Cardiologia");

        Medic updatedMedic = new Medic();
        updatedMedic.setId(1L);
        updatedMedic.setName("Dr. Ana Souza");
        updatedMedic.setSpecialty("Dermatologia");

        given(medicService.findById(anyLong())).willReturn(Optional.of(medic));
        given(medicService.save(any(Medic.class))).willReturn(updatedMedic);

        mockMvc.perform(put("/medics/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedMedic)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Dr. Ana Souza"));
    }

    @Test
    void testDeleteMedic() throws Exception {
        mockMvc.perform(delete("/medics/1"))
                .andExpect(status().isOk());
    }
}
