package com.pedroalmir.athena.core.type.container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;

import com.pedroalmir.athena.core.pattern.Visitor;
import com.pedroalmir.athena.core.type.base.Type;
import com.pedroalmir.athena.core.type.numeric.base.Numeric;
import com.pedroalmir.athena.web.model.vo.type.TypeVO;

/**
 * General list container for all {@code Type} instances.
 *
 */
public class TypeList extends AbstractList<Type> {

	private List<Type> components;

    /**
     * Create a new instance.
     */
    public TypeList() {
        this.components = new ArrayList<Type>();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public TypeList getClone() {

    	TypeList typeList = new TypeList();
    	
    	for(Type type : this.components){
    		typeList.add(type.getClone());
    	}
    	
        return typeList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if ((o == null) || (this.getClass() != o.getClass())) {
            return false;
        }

        TypeList otherList = (TypeList) o;
        return this.components.equals(otherList.components);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.components == null ? 0 : this.components.hashCode());
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type get(int index) {
        return this.components.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(int index, Type value) {
        this.components.set(index, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(int index, Type value) {
        this.components.add(index, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean append(AbstractList<Type> list) {
        for (Type type : list) {
            this.components.add(type);
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean prepend(AbstractList<Type> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            this.components.add(0, list.get(i));
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] toArray() {
        return this.components.toArray();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypeList subList(int fromIndex, int toIndex) {
        // Need to bump up the toIndex because the List.subList() operation is upper bound exclusive.
        List<Type> result = this.components.subList(fromIndex, toIndex + 1);
        TypeList sublist = new TypeList();

        for (Type type : result) {
            sublist.add(type);
        }

        return sublist;
    }

    /**
     * {@inheritDoc}
     */
    public boolean add(Type element) {
        return this.components.add(element);
    }

    /**
     * {@inheritDoc}
     */
    public boolean addAll(Collection<? extends Type> c) {
        return this.components.addAll(c);
    }

    /**
     * {@inheritDoc}
     */
    public void clear() {
        this.components.clear();
    }

    /**
     * {@inheritDoc}
     */
    public boolean contains(Object o) {
        return this.components.contains(o);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isEmpty() {
        return this.components.isEmpty();
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    public Iterator<Type> iterator() {
        return this.components.iterator();
    }

    /**
     * {@inheritDoc}
     */
    public boolean remove(Object o) {
        return this.components.remove(o);
    }

    /**
     * {@inheritDoc}
     */
    public boolean removeAll(Collection<?> c) {
        return this.components.removeAll(c);
    }

    /**
     * {@inheritDoc}
     */
    public int size() {
        return this.components.size();
    }


	/**
	 * Randomize this list
	 */
	public void randomise() {
        for (int i = 0; i < components.size(); i++) {
            Type type = components.get(i);
            if (type instanceof Numeric) {
                Numeric numeric = (Numeric) type;
                numeric.randomise();
            }
        }
    }

    public <T> T[] toArray(T[] a) {
        return this.components.toArray(a);
    }

    public boolean containsAll(Collection<?> c) {
        return this.components.containsAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return this.components.retainAll(c);
    }


	/**
	 * @return the components
	 */
	public List<Type> getComponents() {
		return components;
	}


	/**
	 * @param components the components to set
	 */
	public void setComponents(List<Type> components) {
		this.components = components;
	}


	 /**
     * {@inheritDoc}
     */
    public void accept(Visitor<Type> visitor) {
        for (Type type : this.components) {
            if (!visitor.isDone()) {
                visitor.visit(type);
            }
        }
    }

	public Type getClone(String object) {
		return new TypeList();
	}

	public Object getValue() {
		return this.components;
	}

	@SuppressWarnings("unchecked")
	public void setValue(Object object) {
		this.components = (List<Type>) object;
	}

	public void setValue(String object) {
	}

	public Object getRepresentation() {
		List<String> list = new LinkedList<String>();
		String actual = "";
		int count = 0;
		for(Type t : this.components){
			String previous = actual;
			actual = (String) t.getRepresentation();
			list.add(actual);
			if(previous.equals(actual)){
				count++;
			}
		}
		
		if(list.size() == count){
			return "List[" + actual + "]";
		}else{
			return "List[" + StringUtils.join(list.iterator(), "," ) + "]";
		}
		
	}


	@Override
	public TypeVO getTypeVO() {
		// TODO Auto-generated method stub
		return null;
	}
}
