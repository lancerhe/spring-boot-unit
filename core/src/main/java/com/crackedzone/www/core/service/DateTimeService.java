package com.crackedzone.www.core.service;

import org.springframework.stereotype.Service;

/**
 * Package com.crackedzone.www.core.service
 *
 * @author Lancer He <lancer.he@gmail.com>
 */
@Service
public class DateTimeService {

    public int getCurrentTime() {
        return new Long(getCurrentTimeMillis() / 1000).intValue();
    }

    public long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}