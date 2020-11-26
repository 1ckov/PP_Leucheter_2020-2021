package filter_map_reduce;
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
