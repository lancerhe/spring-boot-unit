package com.crackedzone.www.core.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.annotation.Resource;

/**
 * Package com.crackedzone.www.core.repository
 *
 * @author Lancer He <lancer.he@gmail.com>
 */
class BaseRepository {

    @Resource
    @Qualifier("primaryJdbcTemplate")
    JdbcTemplate primaryJdbcTemplate;

    @Resource
    @Qualifier("primaryNamedJdbcTemplate")
    NamedParameterJdbcTemplate primaryNamedJdbcTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;
}