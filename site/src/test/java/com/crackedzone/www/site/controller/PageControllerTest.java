package com.crackedzone.www.site.controller;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Package com.crackedzone.www.site.controller
 *
 * @author Lancer He <lancer.he@gmail.com>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PageControllerTest extends ControllerAcceptanceTest {

    @Test
    public void response_pages_on_home_page() throws Exception {
        this.mockMvc.perform(get("/pages"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(2000))
                .andExpect(jsonPath("$.pages", hasSize(2)))
                .andExpect(jsonPath("$.pages[0].title").value("公司简介"))
                .andExpect(jsonPath("$.pages[1].title").value("联系我们"));
    }

    @Test
    public void response_page_id_equals_1_on_home_page() throws Exception {
        this.mockMvc.perform(get("/pages/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(2000))
                .andExpect(jsonPath("$.page.*", hasSize(8)))
                .andExpect(jsonPath("$.page.title").value("公司简介"));
    }
}
