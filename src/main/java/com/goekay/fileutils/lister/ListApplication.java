package com.goekay.fileutils.lister;

import com.goekay.fileutils.CommandLineParams;
import com.goekay.fileutils.core.BaseApplication;
import com.goekay.fileutils.core.PathValidator;
import com.goekay.fileutils.core.Reporter;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 15.12.2015
 */
@RequiredArgsConstructor
public class ListApplication extends BaseApplication {
    private final CommandLineParams params;
    private Reporter reporter;

    @Override
    public void validate() {
        new PathValidator().validate(params.getDirectories());
    }

    @Override
    public void start() {
        reporter = initReporter(params);

        // TODO: Might benefit from parallelStream()
        for (Path p : getUnique(params)) {
            ListDirectoryVisitor v = new ListDirectoryVisitor(p);
            walk(p, v);
            reporter.prepare(p, v.getMetaDataList());
        }

        reporter.report();
    }

    /**
     * Clean up duplicate entries
     */
    private Set<Path> getUnique(CommandLineParams params) {
        return new HashSet<>(params.getDirectories());
    }
}
