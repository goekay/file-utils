package com.goekay.fileutils;

import com.beust.jcommander.JCommander;
import com.goekay.fileutils.core.BaseApplication;
import com.goekay.fileutils.differ.DiffApplication;
import com.goekay.fileutils.lister.ListApplication;

import static com.goekay.fileutils.Utils.inputError;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 14.12.2015
 */
public class Application {

    public static void main(String... args) {
        CommandLineParams params = new CommandLineParams();
        JCommander jCommander = new JCommander(params);

        // Print usage
        if (args.length == 0) {
            jCommander.setProgramName("file-utils");
            jCommander.usage();
            return;
        }

        // Parse input args
        try {
            jCommander.parse(args);
        } catch (Exception e) {
            inputError(e);
            return;
        }

        // Do app-specific init, validation and start
        BaseApplication app = initApp(params);
        try {
            app.validate();
        } catch (Exception e) {
            inputError(e);
            return;
        }
        app.start();
    }

    private static BaseApplication initApp(CommandLineParams params) {
        switch (params.getOperationType()) {
            case list   : return new ListApplication(params);
            case diff   : return new DiffApplication(params);
            default     : throw new RuntimeException("Invalid operation");
        }
    }
}
