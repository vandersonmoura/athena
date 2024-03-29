/**           __  __
 *    _____ _/ /_/ /_    Computational Intelligence Library (CIlib)
 *   / ___/ / / / __ \   (c) CIRG @ UP
 *  / /__/ / / / /_/ /   http://cilib.net
 *  \___/_/_/_/_.___/
 */
package com.pedroalmir.athena.core.type;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.pedroalmir.athena.core.type.base.Type;
import com.pedroalmir.athena.web.model.vo.type.TypeVO;

/**
 * Simple {@code Blackboard} implementation.
 *
 * @param <K> The key type.
 * @param <V> The value type.
 */
public final class Blackboard<K, V extends Type> implements Type {

	private final Map<K, V> board;

    /**
     * Create a new empty {@code Blackboard} container.
     */
    public Blackboard() {
        this.board = new HashMap<K, V>();
    }

    /**
     * Copy constructor. Create a copy of the provided instance.
     * @param copy The instance to copy.
     */
    public Blackboard(Blackboard<K, V> copy) {
        this.board = new ConcurrentHashMap<K, V>();
        for (Map.Entry<K, V> entry : copy.board.entrySet()) {
            K key = entry.getKey();
            @SuppressWarnings({"unchecked"})
            V value = (V) entry.getValue().getClone();
            this.board.put(key, value);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Blackboard<K, V> getClone() {
        return new Blackboard<K, V>(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (this.getClass() != obj.getClass())) {
            return false;
        }

        Blackboard<?, ?> other = (Blackboard<?, ?>) obj;
        return this.board.equals(other.board);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.board == null ? 0 : this.board.hashCode());
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.board.toString();
    }

    /**
     * Put the provided key / value pair into the {@code Blackboard}.
     * @param key The key value for the pair.
     * @param value The value associated with the key.
     * @return The provided value.
     */
    public V put(K key, V value) {
        return this.board.put(key, value);
    }

    /**
     * Get the value associated with the provided key, {@code null} otherwise.
     * @param key The key to obtained the value of.
     * @return The associated value to the key.
     */
    public V get(K key) {
        return this.board.get(key);
    }

    /**
     * Obtain a {@code Set} of key / value pairs.
     * @return The set of values.
     */
    public Set<Map.Entry<K, V>> entrySet() {
        return this.board.entrySet();
    }

	public Type getClone(String object) {
		return new Blackboard<K, V>(this);
	}

	public Object getValue() {
		return this.board;
	}

	public void setValue(Object object) {
	}

	public void setValue(String object) {
	}

	public Object getRepresentation() {
		return "properties";
	}

	@Override
	public void clear() {
		for(K key : this.board.keySet()){
			this.board.remove(key);
		}
	}
	
	@Override
	public TypeVO getTypeVO() {
		return new TypeVO(this.getValue(), (String) this.getRepresentation());
	}
}
