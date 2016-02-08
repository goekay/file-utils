package com.goekay.fileutils.core;

import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 08.02.2016
 */
public class IgnoreFileReader {

    private static final String IGNORE_FILE     = "ignorefile";
    private static final String COMMENT_PREFIX  = "#";

    private final List<Pattern> ignorePatterns;

    public IgnoreFileReader() {
        try {
            URL url = this.getClass().getClassLoader().getResource(IGNORE_FILE);

            if (url == null) {
                throw new FileNotFoundException("Could not load the file '" + IGNORE_FILE + "'");
            }

            ignorePatterns = Files.lines(Paths.get(url.toURI()), StandardCharsets.UTF_8)
                                  .map(String::trim)
                                  .filter(this::isValidPattern)
                                  .distinct()
                                  .map(Pattern::compile)
                                  .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isValidPattern(String line) {
        boolean invalid = line == null || line.isEmpty() || line.startsWith(COMMENT_PREFIX);
        return !invalid;
    }

    private boolean skip(String fileName) {
        for (Pattern p : ignorePatterns) {
            if (p.matcher(fileName).matches()) {
                return true;
            }
        }
        return false;
    }

    public boolean isOK(String fileName) {
        return !skip(fileName);
    }
}
