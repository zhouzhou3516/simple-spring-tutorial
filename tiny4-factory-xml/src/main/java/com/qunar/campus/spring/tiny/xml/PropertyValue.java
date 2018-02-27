package com.qunar.campus.spring.tiny.xml;

/**
 * Description: PropertyValue
 *
 * @author yushen.ma
 * @version 2015-03-16 21:28
 */
public class PropertyValue {


    private String name;

    private Object value;

    private Object ref;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public PropertyValue(String name, Object value, Object ref) {
        this.name = name;
        this.value = value;
        this.ref = ref;
    }

    public Object getRef() {
        return ref;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PropertyValue)) return false;

        PropertyValue that = (PropertyValue) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (ref != null ? !ref.equals(that.ref) : that.ref != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (ref != null ? ref.hashCode() : 0);
        return result;
    }
}
