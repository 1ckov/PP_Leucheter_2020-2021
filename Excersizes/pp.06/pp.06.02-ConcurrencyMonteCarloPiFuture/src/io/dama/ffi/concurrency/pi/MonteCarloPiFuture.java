package io.dama.ffi.concurrency.pi;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Berechnung von pi duch Monte Carlo Verfahren: Vergleich der Anzahl von
 * zufällig gewähten Punkten innerhalb eines Viertelkreises (Radius r = 1) bzw.
 * innerhalb eines Quadrates (Kantenlänge 1) analog zu Fläche eines
 * Viertelkreises (pi * r^2 / 4) mit der Fläche des Quadrates (1 = 1 * 1).
 *
 * Sequenzielle Berechnung (im Main-Thread)
 *
 * @author Sandro Leuchter
 *
 */
class MonteCarloPiFuture {
    static int TOTAL_CYCLES = 100000000;
    static int PARALLELNESS = 16;

    public static InOutTuple getResultMonteCarloPiDraw(final int cycles) {
        var in = 0;
        var out = 0;
        var r = new Random();
        for (var i = 0; i < cycles; i++) {
            var x = r.nextDouble();
            var y = r.nextDouble();
            if (Math.sqrt((x * x) + (y * y)) <= 1.0) {
                in++;
            } else {
                out++;
            }
        }
        return new InOutTuple(in, out);
    }

    /**
     * main-Methode
     *
     * @param args Kommandozeilenparameter (nicht benutzt)
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(final String... args) throws InterruptedException, ExecutionException {
        // Stopwatch start
        var before = System.currentTimeMillis();

        // 1. Creating the Executor pool and our Future Storage
        var executor = Executors.newFixedThreadPool(PARALLELNESS);
        var futures = new LinkedList<Future<InOutTuple>>();

        // 2. We create as many new Futures as we have Threads to use
        // also we reduce the cycle amounts by the number of futures
        for (int i = 0; i < PARALLELNESS; i++) {
            futures.add(executor.submit(() -> {
                return getResultMonteCarloPiDraw(TOTAL_CYCLES / PARALLELNESS);
            }));
        }
        // 3. We signal the pool to shutdown after all operations have finished
        executor.shutdown();

        // 4. We bring all the Elements Together into one single InOutTuple
        // We Take out the first element of the Array so we can add on top of it
        InOutTuple result = futures.get(0).get();
        // We run thru the whole array and add every element on top of ours
        for (int i = 1; i < PARALLELNESS; i++) {
            result = result.add(futures.get(i).get());
        }
        var pi = ((4.0 * result.in()) / (result.in() + result.out()));
        var after = System.currentTimeMillis();
        System.out.format("After %d miliseconds the aproximation of pi = %f\n", (after - before), pi);

    }

}
