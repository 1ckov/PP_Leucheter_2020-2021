import java.util.concurrent.atomic.AtomicInteger;

class AtomicFloat extends Number {

    /**
     *
     */
    private static final long serialVersionUID = -3354962088723299121L;
    private final AtomicInteger bits;

    public AtomicFloat() {
        this(0f);
    }

    public AtomicFloat(final float initialValue) {
        this.bits = new AtomicInteger(Float.floatToIntBits(initialValue));
    }

    public final boolean compareAndSet(final float expect, final float update) {
        return this.bits.compareAndSet(Float.floatToIntBits(expect), Float.floatToIntBits(update));
    }

    public final void set(final float newValue) {
        this.bits.set(Float.floatToIntBits(newValue));
    }

    public final float get() {
        return Float.intBitsToFloat(this.bits.get());
    }

    @Override
    public float floatValue() {
        return get();
    }

    public final float getAndSet(final float newValue) {
        return Float.intBitsToFloat(this.bits.getAndSet(Float.floatToIntBits(newValue)));
    }

    public final boolean weakCompareAndSet(final float expect, final float update) {
        return this.bits.weakCompareAndSetPlain(Float.floatToIntBits(expect), Float.floatToIntBits(update));
    }

    @Override
    public double doubleValue() {
        return floatValue();
    }

    @Override
    public int intValue() {
        return (int) get();
    }

    @Override
    public long longValue() {
        return (long) get();
    }

}
