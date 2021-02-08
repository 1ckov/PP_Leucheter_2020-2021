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
 * Parallele Berechnung (mit Futures im FixedThreadPool)
 *
 * @author Sandro Leuchter
 *
 */
class MonteCarloPiFuture {
    static int PARALLELISM = 32;
    static int TOTAL_CYCLES = 1000000000;

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
        var now = System.currentTimeMillis();
        var pool = Executors.newFixedThreadPool(PARALLELISM);
        var futures = new LinkedList<Future<InOutTuple>>();
        for (var i = 0; i < PARALLELISM; i++) {
            futures.add(pool.submit(() -> getResultMonteCarloPiDraw(TOTAL_CYCLES / PARALLELISM)));
        }
        pool.shutdown();
        var result = futures.get(0).get();
        for (var i = 1; i < PARALLELISM; i++) {
            result = result.add(futures.get(i).get());
        }
        var pi = ((4.0 * result.in()) / (result.in() + result.out()));
        System.out.println(pi + ", " + (System.currentTimeMillis() - now) + " ms");
    }

}
