package com.credXp.dao.impl;

import com.credXp.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Slf4j
public class HashMapConverter implements AttributeConverter<Map<String, String>, String> {

    @Override
    public String convertToDatabaseColumn(Map<String, String> customerInfo) {

        String customerInfoJson = null;
        try {
            customerInfoJson = Utils.mapper.writeValueAsString(customerInfo);
        } catch (final JsonProcessingException e) {
            log.error("JSON writing error", e);
        }

        return customerInfoJson;
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String customerInfoJSON) {

        Map<String, String> customerInfo = null;
        try {
            customerInfo = Utils.mapper.readValue(customerInfoJSON,
                    new TypeReference<HashMap<String, String>>() {});
        } catch (IOException e) {
            log.error("JSON reading error", e);
        }

        return customerInfo;
    }
}
