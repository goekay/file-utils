package com.goekay.fileutils.core;

import com.goekay.fileutils.CommandLineParams;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 15.12.2015
 */
public abstract class BaseApplication {

    public abstract void validate();
    public abstract void start();

    public void walk(Path path, BaseVisitor v) {
        try {
            Files.walkFileTree(path, v);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public Reporter initReporter(CommandLineParams c) {
        switch (c.getReportType()) {
            case console    : return new ConsoleReporter(c.getOperationType());
            case file       : return new FileReporter(c.getOperationType());
            default         : return new BaseReporter(c.getOperationType());
        }
    }
}
