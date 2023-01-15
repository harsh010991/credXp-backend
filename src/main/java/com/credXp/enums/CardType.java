package com.credXp.enums;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum CardType {
    CREDIT(0), DEBIT(1);

    private int value;
    CardType(int value) {
        this.value = value;
    }
    public static CardType fromValue(int value) {
        try {
            return CardType.values()[value];
        } catch(ArrayIndexOutOfBoundsException e) {
            log.debug(e.getMessage(), e);
            throw new IllegalArgumentException("Unknown enum value " + value);
        }
    }
}
