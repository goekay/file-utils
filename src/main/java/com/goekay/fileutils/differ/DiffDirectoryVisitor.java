package com.goekay.fileutils.differ;

import com.goekay.fileutils.core.BaseVisitor;
import com.goekay.fileutils.core.FileMetaData;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 11.12.2015
 */
public class DiffDirectoryVisitor extends BaseVisitor {

    public DiffDirectoryVisitor(Path root) {
        super(root);
    }

    @Override
    public void storeMetaData(Path file, BasicFileAttributes attrs) {
        FileMetaData d = new FileMetaData(calcRelativeName(file), attrs.size(), attrs.lastModifiedTime().toMillis());
        fileMetaDataList.add(d);
    }
}
