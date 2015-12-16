package com.goekay.fileutils.core;

import com.goekay.fileutils.OperationType;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.goekay.fileutils.Utils.sep;

/**
 * Does not print/report anything. Only useful when testing.
 *
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 12.12.2015
 */
@RequiredArgsConstructor
public class BaseReporter implements Reporter {
    private final List<ResultHolder> results = new ArrayList<>();
    protected final OperationType type;

    private static final String HEADER_TEMPLATE =
            "// -------------------------------------------------------------------------" + sep() +
            "// %s %s" + sep() +
            "// Number of files: %d" + sep() +
            "// -------------------------------------------------------------------------" + sep();

    public void print(String str) {
        // No-op
    }

    @Override
    public void prepare(Path p, List<FileMetaData> meta) {
        results.add(new ResultHolder(p, meta));
    }

    @Override
    public void report() {
        Iterator<ResultHolder> it = results.iterator();
        if (it.hasNext()) {
            printOnePath(it.next());
            while (it.hasNext()) {
                print("");
                printOnePath(it.next());
            }
        }
    }

    private void printOnePath(ResultHolder rh) {
        print(String.format(HEADER_TEMPLATE, type.getReportTitle(), rh.getRootPath(), rh.getMeta().size()));

        for (FileMetaData f : rh.getMeta()) {
            print(f.getRelativeName(rh.getRootPath()));
        }
    }
}
