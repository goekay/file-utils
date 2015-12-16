package com.goekay.fileutils;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;

import java.util.Arrays;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 14.12.2015
 */
public enum ReportType {
    console, file;

    public static String getValues() {
        return Arrays.toString(ReportType.values());
    }

    public static class Converter implements IStringConverter<ReportType> {
        @Override
        public ReportType convert(String str) {
            for (ReportType r : ReportType.values()) {
                if (r.name().equalsIgnoreCase(str)) {
                    return r;
                }
            }
            throw new ParameterException("Report type '" + str + "' is invalid, should be one of " + getValues());
        }
    }
}
