package com.goekay.fileutils.core;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 15.12.2015
 */
public class PathValidator {

    public void validate(List<Path> value) {
        for (Path path : value) {
            validateOne(path);
        }
    }

    private void validateOne(Path path) {
        if (!Files.exists(path)) {
            throw new RuntimeException(path + " does not exist");

        } else if (!Files.isDirectory(path)) {
            throw new RuntimeException(path + " is not a directory");

        } else if (!path.isAbsolute()) {
            throw new RuntimeException(path + " is not absolute");
        }
    }
}
