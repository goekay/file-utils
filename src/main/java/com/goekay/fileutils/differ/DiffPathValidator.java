package com.goekay.fileutils.differ;

import com.goekay.fileutils.core.PathValidator;

import java.nio.file.Path;
import java.util.List;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 15.12.2015
 */
public class DiffPathValidator extends PathValidator {

    @Override
    public void validate(List<Path> value) {
        if (value.size() != 2) {
            throw new RuntimeException("Was expecting exactly 2 directories, but got " + value.size() + " " + value);
        }

        if (value.get(0).equals(value.get(1))) {
            throw new RuntimeException("Both arguments refer to the same directory");
        }

        super.validate(value);
    }
}
