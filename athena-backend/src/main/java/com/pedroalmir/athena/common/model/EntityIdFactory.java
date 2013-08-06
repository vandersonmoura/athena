package com.pedroalmir.athena.common.model;

import java.util.concurrent.atomic.AtomicLong;

import com.pedroalmir.athena.core.entity.Entity;

/**
 *
 */
public final class EntityIdFactory {
    
	private static ThreadLocal<AtomicLong> sequence = new ThreadLocal<AtomicLong>() {
        @Override
        protected AtomicLong initialValue() {
            return new AtomicLong(0);
        }
    };

    private EntityIdFactory() {
    	
    }

    /**
     * Get the next number in the sequence as an {@code id} for the {@link Entity}. This
     * sequence is defined per thread and is defined to start counting from {@code 1}.
     * @return the next number in the sequence.
     */
    public static long getNextId() {
        AtomicLong instance = sequence.get();
        return instance.getAndIncrement() + 1;
    }

    public static void remove() {
        sequence.remove();
    }
}
