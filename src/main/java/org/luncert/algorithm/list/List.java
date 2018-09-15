package org.luncert.algorithm.list;

import java.util.function.BiFunction;

public interface List<E> extends Iterable<E> {

    void add(E data);

    void add(int index, E data);

    E remove(int index);

    E remove(E data);

    E set(int index, E data);

    E set(E oldValue, E newValue);

    E get(int index);

    int size();
    
    void clear();
    
    E max(BiFunction<E, E, Boolean> compare);

    E min(BiFunction<E, E, Boolean> compare);
    
    void sort(BiFunction<E, E, Boolean> compare);

}