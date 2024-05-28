package com.example.net0backend.controller;

import com.example.net0backend.auth.JWTAuthenticationFilter;
import com.example.net0backend.auth.JWTProvider;
import com.example.net0backend.config.WebOAuthSecurityConfig;
import com.example.net0backend.dto.request.MyLocationRequest;
import com.example.net0backend.service.StoreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.security.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.data.jpa.util.JpaMetamodel;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StoreController.class,
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = {JWTAuthenticationFilter.class, JWTProvider.class, WebOAuthSecurityConfig.class})
})
@MockBean(JpaMetamodelMappingContext.class)
class StoreControllerTest {

    @MockBean
    private StoreService storeService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser("ROLE_USER")
    @DisplayName("내 주변의 가게 목록을 가져 온다. (메인 페이지)")
    public void getNearStoreList() throws Exception {
        //given
        MyLocationRequest myLocationRequest = new MyLocationRequest(37.123456, 127.123456);

        //when //then
        mockMvc.perform(
                get("/api/v1/store/list")
                        .param("x", String.valueOf(myLocationRequest.getX()))
                        .param("y", String.valueOf(myLocationRequest.getY()))
        )
                .andDo(print())
                .andExpect(status().isOk());
    }
}