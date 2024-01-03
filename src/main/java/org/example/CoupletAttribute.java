package org.example;

public class CoupletAttribute
{
    private AttributesEnum attribute;

    public AttributesEnum getAttribute() {
        return attribute;
    }

    public double getValue() {
        return value;
    }

    public CoupletAttribute(AttributesEnum attribute, double value) {
        this.attribute = attribute;
        this.value = value;
    }

    private double value;


}
