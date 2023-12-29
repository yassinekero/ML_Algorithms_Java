package org.example;




public class Couplet
{
  private OriginsEnum origin;
    private float euclDist;

    public OriginsEnum getOrigin() {
        return origin;
    }

    public float getEuclDist() {
        return euclDist;
    }

    public Couplet(OriginsEnum origin, float euclDit) {
        this.origin = origin;
        this.euclDist = euclDit;
    }

    @Override
    public String toString() {
        return "Couplet{" +
                "origin=" + origin +
                ", euclDit=" + euclDist +
                '}';
    }
}
