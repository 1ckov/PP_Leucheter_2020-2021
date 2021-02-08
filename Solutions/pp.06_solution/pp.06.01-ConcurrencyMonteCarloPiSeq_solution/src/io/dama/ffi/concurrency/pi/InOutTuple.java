package io.dama.ffi.concurrency.pi;

// this class is identical to public record (int in, int out){} (preview since Java 14, Mar 2020)

public final class InOutTuple {
    private final int in;
    private final int out;

    public InOutTuple(final int in, final int out) {
        this.in = in;
        this.out = out;
    }

    public int in() {
        return this.in;
    }

    public int out() {
        return this.out;
    }

    @Override
    public String toString() {
        return String.format("InOutTuple [in=%s, out=%s]", this.in, this.out);
    }

    @Override
    public int hashCode() {
        var prime = 31;
        var result = 1;
        result = (prime * result) + this.in;
        result = (prime * result) + this.out;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof InOutTuple)) {
            return false;
        }
        var other = (InOutTuple) obj;
        if (this.in != other.in) {
            return false;
        }
        if (this.out != other.out) {
            return false;
        }
        return true;
    }
}
