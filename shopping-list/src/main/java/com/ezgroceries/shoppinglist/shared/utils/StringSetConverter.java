package com.ezgroceries.shoppinglist.shared.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.springframework.util.CollectionUtils;

/**
 * @author Gert
 * @since release 202008 / (2020-06-09)
 */
@Converter
public class StringSetConverter implements AttributeConverter<Set<String>, String> {

    @Override
    public String convertToDatabaseColumn(Set<String> set) {
        if (!CollectionUtils.isEmpty(set)) {
            return "," + String.join(",", set) + ",";
        } else {
            return null;
        }
    }

    @Override
    public Set<String> convertToEntityAttribute(String joined) {
        if (joined != null) {
            String values = joined.substring(1, joined.length() - 1); //Removes leading and trailing commas
            return new HashSet<>(Arrays.asList(values.split(",")));
        }
        return new HashSet<>();
    }
}