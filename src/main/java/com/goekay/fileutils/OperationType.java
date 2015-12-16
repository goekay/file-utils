package com.goekay.fileutils;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 15.12.2015
 */
@Getter
@RequiredArgsConstructor
public enum OperationType {
    list("Files in"),
    diff("Only in");

    private final String reportTitle;

    public static String getValues() {
        return Arrays.toString(OperationType.values());
    }

    public static class Converter implements IStringConverter<OperationType> {
        @Override
        public OperationType convert(String str) {
            for (OperationType r : OperationType.values()) {
                if (r.name().equalsIgnoreCase(str)) {
                    return r;
                }
            }
            throw new ParameterException("Operation type '" + str + "' is invalid, should be one of " + getValues());
        }
    }
}
