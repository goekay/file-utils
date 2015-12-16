package com.goekay.fileutils.core;

import com.goekay.fileutils.OperationType;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 12.12.2015
 */
public class ConsoleReporter extends BaseReporter {

    public ConsoleReporter(OperationType type) {
        super(type);
    }

    @Override
    public void print(String str) {
        System.out.println(str);
    }

}
