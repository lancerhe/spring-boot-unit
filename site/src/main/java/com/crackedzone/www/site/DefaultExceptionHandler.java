package com.crackedzone.www.site;

import com.crackedzone.www.core.repository.RecordNotFoundException;
import com.crackedzone.www.core.util.HttpResponseUtils;
import com.crackedzone.www.core.util.HttpResponseUtils.HttpResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Package com.crackedzone.www.site
 *
 * @author Lancer He <lancer.he@gmail.com>
 */

@ControllerAdvice
class DefaultExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(RecordNotFoundException.class)
    public String recordNotFound(RecordNotFoundException e) {
        logger.error(e.getMessage());
        return HttpResponseUtils.failed(HttpResponseCode.RECORD_NOT_FOUND, e.getMessage()).toString();
    }
}