package com.goekay.fileutils.core;

import com.goekay.fileutils.OperationType;
import com.goekay.fileutils.Utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 12.12.2015
 */
public class FileReporter extends BaseReporter {

    private StringBuilder stringBuilder = new StringBuilder();
    private static final String fileNameTemplate = "dir-%s-report_%s.txt";

    public FileReporter(OperationType type) {
        super(type);
    }

    @Override
    public void print(String str) {
        stringBuilder.append(str)
                     .append(System.lineSeparator());
    }

    @Override
    public void report() {
        super.report();
        dumpToFile();
    }

    private void dumpToFile() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH.mm.ss")
                                                 .withZone(ZoneId.systemDefault());

        String stopStr = dtf.format(Instant.now());
        String fileName = String.format(fileNameTemplate, type.name(), stopStr);
        Path p = Paths.get(fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(p, StandardCharsets.UTF_8)) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Utils.print("Report file: " + p.toAbsolutePath());
    }
}
