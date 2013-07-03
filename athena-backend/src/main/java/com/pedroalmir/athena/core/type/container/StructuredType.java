package com.pedroalmir.athena.core.type.container;

import java.util.Collection;

import com.pedroalmir.athena.core.pattern.Visitor;
import com.pedroalmir.athena.core.type.base.Randomisable;
import com.pedroalmir.athena.core.type.base.Type;
/**
 * Description for all objects that maintain a structure or collection of objects.
 *
 * @param <E> the type of object the structure may contain.
 */
public interface StructuredType<E> extends Collection<E>, Type, Randomisable {

    StructuredType<E> getClone();

    /**
     * Accept the {@linkplain Visitor} instance and perform the actions within the
     * {@linkplain Visitor} on the objects contained within this structure.
     * @param visitor The {@linkplain Visitor} instance to execute.
     */
    void accept(Visitor<E> visitor);

}
