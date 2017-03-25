package com.crackedzone.www.core.repository;

import com.crackedzone.www.core.entity.ManagerEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Package com.crackedzone.www.core.repository
 *
 * @author Lancer He <lancer.he@gmail.com>
 */
@Repository
public class ManagerRepository {

    @Resource
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    @Resource
    @Qualifier("primaryNamedJdbcTemplate")
    private NamedParameterJdbcTemplate primaryNamedJdbcTemplate;

    public ManagerEntity findByUsername(String username) throws RecordNotFoundException {
        List<ManagerEntity> entities = primaryJdbcTemplate.query("SELECT * FROM managers WHERE username = ?", new Object[]{username}, new BeanPropertyRowMapper<>(ManagerEntity.class));
        if (entities.isEmpty()) throw new RecordNotFoundException();
        return entities.get(0);
    }
}