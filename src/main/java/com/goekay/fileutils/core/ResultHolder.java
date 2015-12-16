package com.goekay.fileutils.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;
import java.util.List;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 15.12.2015
 */
@Getter
@RequiredArgsConstructor
public class ResultHolder {
    private final Path rootPath;
    private final List<FileMetaData> meta;
}
