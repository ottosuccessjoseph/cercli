package com.cercli.common.domain.value.object;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Success.Otto
 * @since 0.0.1
 * @param <T>
 */
public abstract class BaseId<T> implements Serializable {
    private final T value;

    protected BaseId(T value) { this.value = value; }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseId)) return false;
        BaseId<?> baseId = (BaseId<?>) o;
        return Objects.equals(getValue(), baseId.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}

