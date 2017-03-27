package com.crackedzone.www.core.repository;

import com.crackedzone.www.core.entity.ManagerEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Package com.crackedzone.www.core.repository
 *
 * @author Lancer He <lancer.he@gmail.com>
 */
@Repository
public class ManagerRepository extends BaseRepository {

    public ManagerEntity findByUsername(String username) throws RecordNotFoundException {
        List<ManagerEntity> entities = primaryJdbcTemplate.query("SELECT * FROM managers WHERE username = ?", new Object[]{username}, new BeanPropertyRowMapper<>(ManagerEntity.class));
        if (entities.isEmpty()) throw new RecordNotFoundException();
        return entities.get(0);
    }
}