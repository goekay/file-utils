package com.goekay.fileutils.lister;

import com.goekay.fileutils.core.BaseVisitor;
import com.goekay.fileutils.core.FileMetaData;
import com.goekay.fileutils.core.IgnoreFileReader;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 11.12.2015
 */
public class ListDirectoryVisitor extends BaseVisitor {
    private final IgnoreFileReader ignoreFileReader;

    public ListDirectoryVisitor(Path root, IgnoreFileReader ignoreFileReader) {
        super(root);
        this.ignoreFileReader = ignoreFileReader;
    }

    @Override
    public void storeMetaData(Path file, BasicFileAttributes attrs) {
        String name = file.getFileName().toString();
        if (ignoreFileReader.isOK(name)) {
            FileMetaData d = new FileMetaData(calcRelativeName(file));
            fileMetaDataList.add(d);
        }
    }
}
