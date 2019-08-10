package com.epam.rd.define_bean;

public class Parameter {

    public final ParameterType type;
    public final String value;

    public Parameter(ParameterType type, String value) {
        this.type = type;
        this.value = value;
    }

    public <T> T asClass(Class<T> asClass) {
        if (type != ParameterType.VAL) {
            throw new IllegalStateException();
        }

        if (int.class.equals(asClass)) {
          //  return asClass.cast(Integer.valueOf(value));
            return (T)Integer.valueOf(value);
        } else if (long.class.equals(asClass)) {
            return (T)Long.valueOf(value);
        } else if (float.class.equals(asClass)) {
            return (T)Float.valueOf(value);
        } else if (double.class.equals(asClass)) {
            return (T)Double.valueOf(value);
        } else if (boolean.class.equals(asClass)) {
            return (T)Boolean.valueOf(value);
        } else if (char.class.equals(asClass)) {
            return (T)Character.valueOf(value.charAt(0));
        } else if (String.class.equals(asClass)) {
            return (T) value;
        }

        return null;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
