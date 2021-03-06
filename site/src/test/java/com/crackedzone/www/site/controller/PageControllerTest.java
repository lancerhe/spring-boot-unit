package com.crackedzone.www.site.controller;

import com.crackedzone.www.core.entity.PageEntity;
import com.crackedzone.www.core.service.DateTimeService;
import com.crackedzone.www.site.WebSecurityConfig;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @MockBean
    private DateTimeService dateTimeService;

    @Test
    public void response_pages_on_home_page() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = get("/pages")
                .sessionAttr(WebSecurityConfig.SESSION_KEY, "admin");
        this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(2000))
                .andExpect(jsonPath("$.pages", hasSize(2)))
                .andExpect(jsonPath("$.pages[0].title").value("公司简介"))
                .andExpect(jsonPath("$.pages[1].title").value("联系我们"));
    }

    @Test
    public void response_page_id_equals_1_on_home_page() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = get("/pages/1")
                .sessionAttr(WebSecurityConfig.SESSION_KEY, "admin");
        this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(2000))
                .andExpect(jsonPath("$.page.*", hasSize(8)))
                .andExpect(jsonPath("$.page.title").value("公司简介"));
    }

    @Test
    public void response_success_while_page_create() throws Exception {
        when(dateTimeService.getCurrentTime()).thenReturn(123);
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = post("/pages/create")
                .param("title", "TITLE")
                .param("cname", "CNAME")
                .param("content", "Content")
                .param("publish_date", "2017-02-22")
                .sessionAttr(WebSecurityConfig.SESSION_KEY, "admin");
        this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(2000));

        PageEntity entity = primaryJdbcTemplate.query(
                "SELECT * FROM pages ORDER BY id DESC LIMIT 1",
                new BeanPropertyRowMapper<>(PageEntity.class)
        ).get(0);
        assertEquals("CNAME", entity.getCname());
        assertEquals("TITLE", entity.getTitle());
        assertEquals("Content", entity.getContent());
        assertEquals("2017-02-22", entity.getPublishDate().toString());
        assertEquals(123, entity.getCreateTime());
        assertEquals(0, entity.getUpdateTime());
    }

    @Test
    public void response_success_while_page_update() throws Exception {
        when(dateTimeService.getCurrentTime()).thenReturn(456);
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = post("/pages/save")
                .param("id", "2")
                .param("title", "TITLE")
                .param("cname", "CNAME")
                .param("content", "Content")
                .param("publish_date", "2017-02-22")
                .sessionAttr(WebSecurityConfig.SESSION_KEY, "admin");
        this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(2000));

        PageEntity entity = primaryJdbcTemplate.query(
                "SELECT * FROM pages WHERE id = 2",
                new BeanPropertyRowMapper<>(PageEntity.class)
        ).get(0);
        assertEquals("CNAME", entity.getCname());
        assertEquals("TITLE", entity.getTitle());
        assertEquals("Content", entity.getContent());
        assertEquals("2017-02-22", entity.getPublishDate().toString());
        assertEquals(456, entity.getUpdateTime());
    }
}
