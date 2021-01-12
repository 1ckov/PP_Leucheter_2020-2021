package io.dama.ffi.concurrency.pi;

import java.util.Random;

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
    static int TOTAL_CYCLES = 10000000;

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
        final var result = getResultMonteCarloPiDraw(TOTAL_CYCLES);
        final var pi = ((4.0 * result.in()) / (result.in() + result.out()));
        System.out.println(pi + ", " + (System.currentTimeMillis() - now) + " ms");
    }

}
