package com.goekay.fileutils.differ;

import com.goekay.fileutils.CommandLineParams;
import com.goekay.fileutils.core.BaseApplication;
import com.goekay.fileutils.core.FileMetaData;
import com.goekay.fileutils.core.IgnoreFileReader;
import com.goekay.fileutils.core.Reporter;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;
import java.util.List;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 15.12.2015
 */
@RequiredArgsConstructor
public class DiffApplication extends BaseApplication {
    private final CommandLineParams params;
    private Reporter reporter;

    @Override
    public void validate() {
        new DiffPathValidator().validate(params.getDirectories());
    }

    /**
     * Consists of three steps:
     * 1. Traverse the trees recursively and collect metadata about files
     * 2. Diff the metadata from the first against the other dir
     * 3. Report
     */
    @Override
    public void start() {
        reporter = initReporter(params);

        Path first = params.getDirectories().get(0);
        Path other = params.getDirectories().get(1);

        List<FileMetaData> firstMetaList = walkInternal(first);
        List<FileMetaData> otherMetaList = walkInternal(other);

        ItemComparator comp = new ItemComparator();
        comp.setFirst(firstMetaList);
        comp.setOther(otherMetaList);
        comp.run();

        reporter.prepare(first, comp.getOnlyInFirst());
        reporter.prepare(other, comp.getOnlyInOther());
        reporter.report();
    }

    private List<FileMetaData> walkInternal(Path p) {
        IgnoreFileReader ignoreFileReader = new IgnoreFileReader();
        DiffDirectoryVisitor v = new DiffDirectoryVisitor(p, ignoreFileReader);
        walk(p, v);
        return v.getMetaDataList();
    }
}
