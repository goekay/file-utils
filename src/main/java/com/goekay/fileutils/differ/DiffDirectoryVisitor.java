package com.goekay.fileutils.differ;

import com.goekay.fileutils.core.BaseVisitor;
import com.goekay.fileutils.core.FileMetaData;
import com.goekay.fileutils.core.IgnoreFileReader;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 11.12.2015
 */
public class DiffDirectoryVisitor extends BaseVisitor {
    private final IgnoreFileReader ignoreFileReader;

    public DiffDirectoryVisitor(Path root, IgnoreFileReader ignoreFileReader) {
        super(root);
        this.ignoreFileReader = ignoreFileReader;
    }

    @Override
    public void storeMetaData(Path file, BasicFileAttributes attrs) {
        Path fileName = file.getFileName();
        if (fileName != null && ignoreFileReader.isOK(fileName.toString())) {
            FileMetaData d = new FileMetaData(calcRelativeName(file), attrs.size(), attrs.lastModifiedTime().toMillis());
            fileMetaDataList.add(d);
        }
    }
}
