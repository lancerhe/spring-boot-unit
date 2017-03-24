package com.crackedzone.www.core.repository;

/**
 * Package com.crackedzone.www.core.repository
 *
 * @author Lancer He <lancer.he@gmail.com>
 */
public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException() {
        super("Record not found.");
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}
