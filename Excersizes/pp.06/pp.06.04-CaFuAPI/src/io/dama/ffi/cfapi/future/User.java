package io.dama.ffi.cfapi.future;

public class User {
    static final int RUN = 10;

    public static void main(final String... args) {
        ILibAsync lib = new LibAsync();
        for (var i = 0; i <= User.RUN; i++) {
            try {
                System.out.printf("%2d: %d\n", i, lib.calcAsync().get());
            } catch (Exception e) {
                System.out.printf("%2d: Fehler (%s)\n", i, e.getMessage());
            }
        }
    }
}
