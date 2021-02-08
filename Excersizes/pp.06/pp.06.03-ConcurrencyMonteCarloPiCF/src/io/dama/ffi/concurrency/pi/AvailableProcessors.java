package io.dama.ffi.concurrency.pi;

import java.util.concurrent.ForkJoinPool;


public class AvailableProcessors {

    public static int getParallelism(){
        return ForkJoinPool.getCommonPoolParallelism();
    }
    public static void main(String[] args) {
        System.out.println(ForkJoinPool.commonPool().getParallelism());
        System.out.println(Runtime.getRuntime().availableProcessors());
    } 
}
