package filter_map_reduce;

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
