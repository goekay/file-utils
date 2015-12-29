package com.goekay.fileutils.core;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static com.goekay.fileutils.Utils.warn;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 11.12.2015
 */
@RequiredArgsConstructor
public abstract class BaseVisitor extends SimpleFileVisitor<Path> {
    private final Path root;

    protected List<FileMetaData> fileMetaDataList = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        storeMetaData(file, attrs);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        warn("Access was denied to " + file);
        return FileVisitResult.SKIP_SUBTREE;
    }

    public List<FileMetaData> getMetaDataList() {
        return this.fileMetaDataList;
    }

    public String calcRelativeName(Path name) {
        // Get the relative-to-root path
        return root.relativize(root.resolve(name)).toString();
    }

    public abstract void storeMetaData(Path file, BasicFileAttributes attrs);
}
