package com.goekay.fileutils.core;

import lombok.EqualsAndHashCode;

import java.nio.file.Path;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 11.12.2015
 */
@EqualsAndHashCode
public class FileMetaData implements Comparable<FileMetaData> {
    private final Path name;
    private long size;
    private long lastModified;

    public FileMetaData(Path name) {
        this.name = name;
    }

    public FileMetaData(Path name, long size, long lastModified) {
        this.name = name;
        this.size = size;
        this.lastModified = lastModified;
    }

    public String getRelativeName(Path root) {
        // Get the relative-to-root path to print
        return root.relativize(root.resolve(name)).toString();
    }

    public Path getName() {
        return this.name;
    }

    /**
     * Is used to sort the result list by name
     */
    public int compareTo(FileMetaData o) {
        return name.compareTo(o.getName());
    }
}
