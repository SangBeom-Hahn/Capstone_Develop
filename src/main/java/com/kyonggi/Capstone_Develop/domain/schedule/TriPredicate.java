package com.kyonggi.Capstone_Develop.domain.schedule;
@FunctionalInterface
public interface TriPredicate <T, U, V> {
    boolean test(T t, U u, V v);
}
