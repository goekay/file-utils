package com.goekay.fileutils;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Sevket Goekay <goekay@dbis.rwth-aachen.de>
 * @since 12.12.2015
 */
public class ApplicationBenchmark {

    @Benchmark
    public void list() throws IOException {
        Application.main("-o", "list", "-d", "C:\\Users\\SG\\Downloads", "D:\\Contemporary");
    }

    @Benchmark
    public void diff() throws IOException {
        Application.main("-o", "diff", "-d", "C:\\Users\\SG\\Downloads", "D:\\Contemporary");
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void listTime() throws IOException {
        Application.main("-o", "list", "-d", "C:\\Users\\SG\\Downloads", "D:\\Contemporary");
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void diffTime() throws IOException {
        Application.main("-o", "diff", "-d", "C:\\Users\\SG\\Downloads", "D:\\Contemporary");
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ApplicationBenchmark.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
