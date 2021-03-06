package com.wd.play.support.util;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GenericBuilder<I> {

    private final Supplier<I> constructor;
    private Set<Consumer<I>> modifiers = new HashSet<>();
    private Set<Predicate<I>> verifiers = new HashSet<>();

    private GenericBuilder(Supplier<I> constructor) {
        this.constructor = constructor;
    }

    public static <T> GenericBuilder<T> of(Supplier<T> constructor) {
        return new GenericBuilder<>(constructor);
    }

    public GenericBuilder<I> withVerifiers(Predicate ...predicates) {
        verifiers = Sets.newHashSet(predicates);
        return this;
    }

    public <V> GenericBuilder<I> with(BiConsumer<I, V> consumer, V value) {
        Consumer<I> c = setter -> consumer.accept(setter, value);
        modifiers.add(c);
        return this;
    }

    public I build() {
        I instance = constructor.get();
        modifiers.forEach(modifier -> modifier.accept(instance));
        verifyPredicates(instance);
        modifiers.clear();
        return instance;
    }

    private void verifyPredicates(I value) {
        List<Predicate<I>> violated = verifiers.stream()
                .filter(e -> !e.test(value)).collect(Collectors.toList());

        if (!violated.isEmpty()) {
            throw new IllegalStateException(value.toString()
                    + " violates predicates " + violated);
        }
    }
}
