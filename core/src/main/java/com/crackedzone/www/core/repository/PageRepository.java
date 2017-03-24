package com.crackedzone.www.core.repository;

import com.crackedzone.www.core.entity.PageEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Package com.crackedzone.www.core.repository
 *
 * @author Lancer He <lancer.he@gmail.com>
 */
@Repository
public class PageRepository {

    @Resource
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    public PageEntity findById(int id) {
        List<PageEntity> entities = primaryJdbcTemplate.query("SELECT * FROM pages WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<PageEntity>(PageEntity.class));
        return entities.isEmpty() ? null : entities.get(0);
    }
}
