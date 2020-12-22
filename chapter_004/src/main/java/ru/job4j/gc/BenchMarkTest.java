package ru.job4j.gc;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;

import java.io.IOException;

//@State(Scope.Benchmark)
@Warmup(iterations = 1)
//@Measurement(iterations = 8)
@BenchmarkMode(Mode.AverageTime)

public class BenchMarkTest {
    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    public int pr() {
        int a = 100 * 10000;
        int b = a / 100;
        return b;
    }
}
