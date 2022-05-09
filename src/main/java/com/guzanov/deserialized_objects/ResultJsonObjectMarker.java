package com.guzanov.deserialized_objects;

/**
 * Интерфейс-маркер.
 * <p>
 * Так как у нас предусмотрено 2 основные операции (Operation.STAT и Operation.SEARCH),
 * то по средствам этого интерфейса обеспечивается полиморфизм.
 */
public interface ResultJsonObjectMarker {
}
