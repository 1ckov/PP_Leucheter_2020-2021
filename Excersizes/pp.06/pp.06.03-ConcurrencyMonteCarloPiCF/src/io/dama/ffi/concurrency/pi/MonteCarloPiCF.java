package io.dama.ffi.concurrency.pi;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

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
class MonteCarloPiCF {
    static int TOTAL_CYCLES = 10000000;
    static int PARALLELISM = 3;

    public static InOutTuple getResultMonteCarloPiDraw(final int cycles) {
        var in = 0;
        var out = 0;
        final var r = new Random();
        for (var i = 0; i < cycles; i++) {
            final var x = r.nextDouble();
            final var y = r.nextDouble();
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
        final var now = System.currentTimeMillis();
        // 1. Create first Compleation Stage
        CompletableFuture<InOutTuple> cf = 
            CompletableFuture.supplyAsync(()-> getResultMonteCarloPiDraw(TOTAL_CYCLES/PARALLELISM))
                // 2. Then we Combine it with the Second Compleation Stage    
                .thenCombineAsync(
                    CompletableFuture.supplyAsync(() -> getResultMonteCarloPiDraw(TOTAL_CYCLES/PARALLELISM))
                    // 2.5 and combine the two Tuple Objects into one
                        ,(final InOutTuple tuple, final InOutTuple otherTuple) -> tuple.add(otherTuple))
                .thenCombineAsync(
                    // 3. Then we Combine the 1,2 Stages which are now one, with the Third Compleation stage
                    CompletableFuture.supplyAsync(() -> getResultMonteCarloPiDraw((TOTAL_CYCLES/PARALLELISM)+1))
                        // 3.5 and combine the Tuple Objects again
                        ,(final InOutTuple tuple, final InOutTuple otherTuple) -> tuple.add(otherTuple));
        //4. Finally we call join and get our InOutTuple with the result back
        final var result = cf.join();
        final var pi = ((4.0 * result.in()) / (result.in() + result.out()));
        System.out.println(pi + ", " + (System.currentTimeMillis() - now) + " ms");
    }

}
