import java.util.concurrent.atomic.AtomicMarkableReference;

class AtomicOneValueBuffer<T> {
    private final AtomicMarkableReference<T> buffer = new AtomicMarkableReference<>(null, false);

    public void put(final T value) {
        do {
            // Kein Inhalt
        } while (!this.buffer.compareAndSet(null, value, false, true));
    }

    public T take() {
        T value = null;
        do {
            value = this.buffer.getReference();
        } while (!this.buffer.compareAndSet(value, null, true, false));
        return value;
    }
}
