package com.crackedzone.www.core.repository;

import com.crackedzone.www.core.entity.PageEntity;
import com.crackedzone.www.core.service.DateTimeService;
import com.crackedzone.www.core.util.DataEntityUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Package com.crackedzone.www.core.repository
 *
 * @author Lancer He <lancer.he@gmail.com>
 */
@Repository
public class PageRepository extends BaseRepository {

    @Resource
    private DateTimeService dateTimeService;

    public List<PageEntity> find(int page, int pageSize) {
        return primaryJdbcTemplate.query("SELECT * FROM pages LIMIT ?, ?", new Object[]{(page - 1) * pageSize, pageSize}, new BeanPropertyRowMapper<>(PageEntity.class));
    }

    public PageEntity findById(int id) throws RecordNotFoundException {
        List<PageEntity> entities = primaryJdbcTemplate.query("SELECT * FROM pages WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(PageEntity.class));
        if (entities.isEmpty()) throw new RecordNotFoundException();
        return entities.get(0);
    }

    public boolean create(PageEntity entity) {
        entity.setCreateTime(dateTimeService.getCurrentTime());
        int affectRows = 0;
        try {
            affectRows = primaryNamedJdbcTemplate.update(
                    DataEntityUtils.generateNamedInsertClause(PageEntity.class),
                    DataEntityUtils.generateInsertMapParameterSource(entity)
            );
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return (affectRows > 0);
    }

    public boolean save(PageEntity entity) {
        entity.setUpdateTime(dateTimeService.getCurrentTime());
        int affectRows = 0;
        try {
            affectRows = primaryNamedJdbcTemplate.update(
                    DataEntityUtils.generateNamedUpdateClause(PageEntity.class, "id"),
                    DataEntityUtils.generateUpdateMapParameterSource(entity, "id")
            );
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return (affectRows > 0);
    }
}