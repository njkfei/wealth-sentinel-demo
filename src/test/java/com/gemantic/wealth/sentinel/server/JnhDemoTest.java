package com.gemantic.wealth.sentinel.server;

import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

public class JnhDemoTest {

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Threads(100)
    public void haha() throws Exception {
        JnhDemo jnhDemo = new JnhDemo();
        jnhDemo.haha();
    }

    @Test
    public void testPid(){
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JnhDemoTest.class.getSimpleName())
                .output(JnhDemoTest.class.getSimpleName())
                .forks(1)
                .warmupIterations(5) //预热次数
                .measurementIterations(5) //真正执行次数
                .build();

        new Runner(opt).run();

    }

}
