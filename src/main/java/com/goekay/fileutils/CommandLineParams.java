package com.goekay.fileutils;

import com.beust.jcommander.Parameter;
import lombok.Getter;

import java.nio.file.Path;
import java.util.List;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 14.12.2015
 */
@Getter
public class CommandLineParams {

    @Parameter(
            names = {"-d", "--dir"},
            description = "The directories to traverse",
            required = true,
            variableArity = true
    )
    private List<Path> directories;

    @Parameter(
            names = {"-o", "--operation"},
            description = "Specifies the operation to execute. "
                    + "'list' will print all the files in the given directories. "
                    + "'diff' will compare and diff the files in the given _two_ directories. "
                    + "In the latter case, the number must be exactly two.",
            converter = OperationType.Converter.class,
            required = true
    )
    private OperationType operationType = OperationType.list;

    @Parameter(
            names = {"-r", "--report"},
            description = "Specifies how the results should be reported at the end. "
                    + "'file' should be used if there will be a lot to print, "
                    + "since 'console' cannot cope with that much data output.",
            converter = ReportType.Converter.class
    )
    private ReportType reportType = ReportType.console;
}
