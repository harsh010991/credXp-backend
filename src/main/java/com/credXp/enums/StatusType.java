package com.credXp.enums;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum StatusType {
    ACTIVE(0), DELETED(1);

    private int value;
    StatusType(int value) {
        this.value = value;
    }
    public static StatusType fromValue(int value) {
        try {
            return StatusType.values()[value];
        } catch(ArrayIndexOutOfBoundsException e) {
            log.debug(e.getMessage(), e);
            throw new IllegalArgumentException("Unknown enum value " + value);
        }
    }
}
