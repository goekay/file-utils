package com.goekay.fileutils.core;

import java.nio.file.Path;
import java.util.List;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 15.12.2015
 */
public interface Reporter {
    void prepare(Path p, List<FileMetaData> meta);
    void report();
}
