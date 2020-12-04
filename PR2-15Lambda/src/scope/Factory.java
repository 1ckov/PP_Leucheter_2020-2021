package src.scope;

import java.util.Comparator;

public class Factory {
    /**
     * Methods can return lambdas.
     * @return - A lambda.
     */
    public Comparator <String> createLexiStringComparator(){
        return (a, b) -> a.compareTo(b);
    }    
}
