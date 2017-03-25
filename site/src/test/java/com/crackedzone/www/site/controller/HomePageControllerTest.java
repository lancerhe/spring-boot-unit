package com.crackedzone.www.site.controller;

import com.crackedzone.www.site.WebSecurityConfig;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Package com.crackedzone.www.site.controller
 *
 * @author Lancer He <lancer.he@gmail.com>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomePageControllerTest extends ControllerAcceptanceTest {

    @Test
    public void response_home_page() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = get("/").sessionAttr(WebSecurityConfig.SESSION_KEY, "admin");
        this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(2000))
                .andExpect(jsonPath("$.message").value("渲染成功"));
    }
}
