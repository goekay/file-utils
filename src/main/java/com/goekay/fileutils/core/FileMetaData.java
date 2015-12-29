package com.goekay.fileutils.core;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 11.12.2015
 */
@EqualsAndHashCode
public class FileMetaData implements Comparable<FileMetaData> {
    @Getter private final String relativeName;
    private long size;
    private long lastModified;

    public FileMetaData(String relativeName) {
        this.relativeName = relativeName;
    }

    public FileMetaData(String relativeName, long size, long lastModified) {
        this.relativeName = relativeName;
        this.size = size;
        this.lastModified = lastModified;
    }

    /**
     * Is used to sort the result list by name
     */
    public int compareTo(FileMetaData o) {
        return relativeName.compareTo(o.getRelativeName());
    }
}
