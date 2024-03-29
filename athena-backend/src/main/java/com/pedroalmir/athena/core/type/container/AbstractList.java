package com.pedroalmir.athena.core.type.container;

import com.pedroalmir.athena.core.type.base.Type;

/**
 * The basic definition for all {@linkplain Type} objects that are based on a list.
 *
 * @param <E> The type element.
 */
public abstract class AbstractList<E extends Type> implements StructuredType<E> {


	/**
     * {@inheritDoc}
     */
    public abstract AbstractList<E> getClone();

    /**
     * Get the {@linkplain Type} at the given index.
     * @param index The index to inspect to return.
     * @return The {@linkplain Type} found at <code>index</code>.
     */
    public abstract E get(int index);

    /**
     * Set the {@linkplain Type} at the index <code>index</code>.
     * @param index The index to set.
     * @param value The value to set.
     */
    public abstract void set(int index, E value);

    /**
     * Insert the provided {@linkplain Type} at the specified {@code index}.
     * @param index The index where to insert the {@linkplain Type}.
     * @param value The value to set.
     */
    public abstract void insert(int index, E value);
    
    /**
     * Get the size of this list
     * @return size
     */
    public abstract int size();

    /**
     * Add the provided {@linkplain Type} to the end of the current list.
     * @param value The {@linkplain Type} to add.
     */
    public void append(E value) {
        int position = size();
        insert(position, value);
    }

    /**
     * Add the provided {@linkplain AbstractList} to the end of the current list.
     * @param list The object to add.
     * @return <code>true</code> if the operation was successful, <code>false</code> otherwise.
     */
    public abstract boolean append(AbstractList<E> list);

    /**
     * Prepend the provided {@linkplain Type} to the from of this list.
     * @param value The {@linkplain Type} to prepend.
     */
    public void prepend(E value) {
        insert(0, value);
    }

    /**
     * Add the provided {@linkplain AbstractList} to the start of the current list.
     * @param list The object to add.
     * @return <code>true</code> if the operation was successful, <code>false</code> otherwise.
     */
    public abstract boolean prepend(AbstractList<E> list);

    /**
     * Create an <code>Object []</code> from this <code>Vector</code>.
     * @return an <code>Object []</code> representing the <code>Vector</code>
     */
    public abstract Object[] toArray();

    /**
     * Create a sub vector from the current {@linkplain Vector}.
     * @param fromIndex The index to start the sub-list from.
     * @param toIndex The last index to end the sub-list at.
     * @return The created sub-list instance.
     */
    public abstract AbstractList<E> subList(int fromIndex, int toIndex);

    /**
     * Create a new (cloned) <tt>Vector</tt> consisting of <tt>rhs</tt> that has been appended to
     * <tt>lhs</tt>.
     * @param <T> The type element.
     * @param lhs The <tt>Vector</tt> that will form the front part of the new (cloned)
     *        <tt>Vector</tt>.
     * @param rhs The <tt>Vector</tt> that will form the back part of the new (cloned)
     *        <tt>Vector</tt>.
     * @return A new <tt>Vector</tt> consisting of the concatenation of <tt>lhs</tt> and
     *         <tt>rhs</tt>.
     */
    public static <T extends Type> AbstractList<T> append(AbstractList<T> lhs, AbstractList<T> rhs) {
        AbstractList<T> cat = lhs.getClone();
        cat.append(rhs.getClone());
        return cat;
    }

    /**
     * Create a new (cloned) <tt>Vector</tt> consisting of <tt>rhs</tt> that has been prepended
     * to <tt>lhs</tt>.
     * @param <T>
     * @param lhs The <tt>Vector</tt> that will form the back part of the new (cloned)
     *        <tt>Vector</tt>.
     * @param rhs The <tt>Vector</tt> that will form the front part of the new (cloned)
     *        <tt>Vector</tt>.
     * @return A new <tt>Vector</tt> consisting of the concatenation of <tt>rhs</tt> and
     *         <tt>lhs</tt>.
     */
    public static <T extends Type> AbstractList<T> prepend(AbstractList<T> lhs, AbstractList<T> rhs) {
        AbstractList<T> cat = rhs.getClone();
        cat.append(lhs.getClone());
        return cat;
    }

    /**
     * Generate a <tt>String</tt> representation of this <tt>Vector</tt> using the provided
     * first, last and delimiter characters.
     * <p>
     * Example Input: Assume <tt>first</tt> = <code>'['</code>, <tt>last</tt> =
     * <code>']'</code>, <tt>delimiter</tt> = <code>','</code> and elements of the
     * <tt>Vector</tt> = {1,2,3,4,5} <br>
     * Example Output: <code>[1,2,3,4,5]</code>
     * <p>
     * In the case where first and last characters are not desired, call the function as follows:
     * <br>
     * <code>toString((char)0, (char)0, ',');</code> The delimiter character may be any character
     * including a tab <code>'\t'</code> or a newline <code>'\n'</code>.
     * @param first The character that indicates the start of the <tt>Vector</tt>
     *        <tt>String</tt>
     * @param last The character that indicates the end of the <tt>Vector</tt>
     *        <tt>String</tt>
     * @param delimiter The character used to delimit the elements of the <tt>Vector</tt>
     * @return a <tt>String</tt> representing this <tt>Vector</tt>
     */
    public String toString(char first, char last, char delimiter) {
        int dimension = size();
        StringBuilder tmp = new StringBuilder(10 * dimension);
        if (first != 0) {
            tmp.append(first);
        }
        if (dimension > 0) {
            tmp.append(this.get(0).toString());
            for (int i = 1; i < dimension; ++i) {
                tmp.append(delimiter);
                tmp.append(this.get(i).toString());
            }
        }
        if (last != 0) {
            tmp.append(last);
        }
        return tmp.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return toString('[', ']', ',');
    }

    /**
     * Get the {@code String} representation, using the provided {@literal delimiter}. Also see
     * @see Object#toString()
     * @param delimiter The delimiter to use.
     * @return The {@linkplain String} representation, using the provided delimiter.
     */
    public String toString(char delimiter) {
        return toString('[', ']', delimiter);
    }
}
