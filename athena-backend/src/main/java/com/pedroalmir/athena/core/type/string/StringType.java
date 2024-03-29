package com.pedroalmir.athena.core.type.string;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import com.pedroalmir.athena.core.type.base.Type;
import com.pedroalmir.athena.web.model.vo.type.TypeVO;

/**
 * A general for string that may be returned from {@code measurement} instances.
 * The {@code StringType} represents a free form string for additional data. The
 * {@code StringType} replaces all occurring {@code ' '} with {@code '_'} to
 * prevent any potential problems with the format of CIlib output files.
 * <p>
 * It is not possible to determine what the delimiter within a data file will be
 * as a result the whitespace is "escaped".
 *
 */
public class StringType implements Type {
    
	/**
     * String value
     */
    private String string;

    /**
     * Create an instance with the given string as the contents. All whitespace
     * is replaced with {@code '_'}.
     * @param string value to set, before replacement.
     */
    public StringType(String string) {
        this.string = checkNotNull(string).replaceAll(" ", "_");
    }

    /**
     * Copy constructor. Copy the given instance.
     * @param copy The instance to copy.
     */
    public StringType(StringType copy) {
        this.string = copy.string;
    }

    /**
     * {@inheritDoc}
     */
    public StringType getClone() {
        return new StringType(this);
    }

    /**
     * Get the contained string.
     * @return the contained {@linkplain String}.
     */
    public String getString() {
        return this.string;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if ((other == null) || (this.getClass() != other.getClass())) {
            return false;
        }

        StringType stringType = (StringType) other;
        return this.string.equals(stringType.string);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.string.hashCode();
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return string;
    }

    /**
     * Write the contents of the {@linkplain StringType} to an {@linkplain ObjectOutput}.
     * @param oos the {@linkplain ObjectOutput} to write to.
     * @throws IOException If an exception occurs.
     */
    public void writeExternal(ObjectOutput oos) throws IOException {
        oos.writeUTF(this.string);
    }

    /**
     * Read the contents from a given {@linkplain ObjectInput}.
     * @param ois The {@linkplain ObjectInput} to use.
     * @throws IOException If an IO problem occurs.
     * @throws ClassNotFoundException If the desired class is not found.
     */
    public void readExternal(ObjectInput ois) throws IOException, ClassNotFoundException {
        this.string = ois.readUTF();
    }

	public Object getValue() {
		return this.string;
	}
	
	public void setValue(Object object) {
		this.string = (String) object;
	}

	public Object getRepresentation() {
		return "string";
	}

	public void setValue(String object) {
		this.string = object;
	}

	public Type getClone(String object) {
		return new StringType(object);
	}

	@Override
	public void clear() {
		this.string = null;
	}
	
	@Override
	public TypeVO getTypeVO() {
		return new TypeVO(this.getValue(), (String) this.getRepresentation());
	}

}
