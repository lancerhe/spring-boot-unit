package com.crackedzone.www.site.controller;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Package com.smartchoiceads.sdk.business
 *
 * @author Lancer He <lancer.he@gmail.com>
 */
class ControllerAcceptanceTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;

    @Resource
    @Qualifier("primaryJdbcTemplate")
    JdbcTemplate primaryJdbcTemplate;

    @Resource
    @Qualifier("primaryNamedJdbcTemplate")
    NamedParameterJdbcTemplate primaryNamedJdbcTemplate;

    MockMvc mockMvc;

    private static boolean isDataFixtures = false;

    @Before
    public void setUpContext() throws Exception {
        // this is where the magic happens, we actually do "by hand" what the spring runner would do for us,
        // read the JavaDoc for the class bellow to know exactly what it does, the method names are quite accurate though
        TestContextManager testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);
    }

    @Before
    public void setUpApplicationMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Before
    public void setUpDataFixtures() throws SQLException {
        if (!isDataFixtures) {
            loadSQL("sql/setup-schema.sql");
            isDataFixtures = true;
        }
        loadSQL("sql/setup-truncate.sql");
        loadSQL("sql/setup-data.sql");
    }

    void loadSQL(String resourceFileName) throws SQLException {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource(resourceFileName));
        resourceDatabasePopulator.populate(primaryDataSource.getConnection());
    }
}
