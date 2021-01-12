package io.dama.ffi.concurrency.pi;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * Berechnung von pi duch Monte Carlo Verfahren: Vergleich der Anzahl von
 * zufällig gewähten Punkten innerhalb eines Viertelkreises (Radius r = 1) bzw.
 * innerhalb eines Quadrates (Kantenlänge 1) analog zu Fläche eines
 * Viertelkreises (pi * r^2 / 4) mit der Fläche des Quadrates (1 = 1 * 1).
 *
 * Parallele Berechnung (im Common Pool über Completable Future)
 *
 * @author Sandro Leuchter
 *
 */
class MonteCarloPiCF2 {
    static int TOTAL_CYCLES = 10000000;
    static int PARALLELISM = 8;

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
     */
    public static void main(final String... args) {
        var now = System.currentTimeMillis();
        var resultStage = CompletableFuture.supplyAsync(() -> getResultMonteCarloPiDraw(TOTAL_CYCLES / PARALLELISM));
        for (var i = 1; i < PARALLELISM; i++) {
            resultStage = resultStage.thenCombineAsync(
                    CompletableFuture.supplyAsync(() -> getResultMonteCarloPiDraw(TOTAL_CYCLES / PARALLELISM)),
                    (final InOutTuple x, final InOutTuple y) -> x.add(y));
        }
        var result = resultStage.join();
        var pi = ((4.0 * result.in()) / (result.in() + result.out()));
        System.out.println(pi + ", " + (System.currentTimeMillis() - now) + " ms");
    }

}
