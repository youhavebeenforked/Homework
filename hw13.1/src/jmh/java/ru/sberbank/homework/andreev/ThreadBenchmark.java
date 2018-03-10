package ru.sberbank.homework.andreev;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Fork(5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@State(Scope.Benchmark)
public class ThreadBenchmark {

    @Benchmark
    public void singleThread(){
        ParallelThreads.singleThreadExecution();
    }

    @Benchmark
    public void multiThread(){
        ParallelThreads.multiThreadExecution();
    }
}
