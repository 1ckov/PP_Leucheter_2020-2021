package src.filter_map_reduce;
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
