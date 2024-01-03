package org.example;




public class Couplet
{
  private OriginsEnum origin;

    public OriginsEnum getOrigin() {
        return origin;
    }

    public void setOrigin(OriginsEnum origin) {
        this.origin = origin;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    private float value;

    public Couplet(OriginsEnum origin, float value) {
        this.origin = origin;
        this.value = value;
    }
}
