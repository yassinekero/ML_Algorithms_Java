package org.example;

public class DataEntry
{
    private int mpg;
    private int displacement;
    private int horsepower;
    private int weight;
    private int acceleration;
    private OriginsEnum origin;
    public DataEntry( int mpg, int displacement, int horsepower, int weight, int acceleration, OriginsEnum origin) {
        this.mpg = mpg;
        this.displacement = displacement;
        this.horsepower = horsepower;
        this.weight = weight;
        this.acceleration = acceleration;
        this.origin = origin;
    }

    public DataEntry( int mpg, int displacement, int horsepower, int weight, int acceleration) {
        this.mpg = mpg;
        this.displacement = displacement;
        this.horsepower = horsepower;
        this.weight = weight;
        this.acceleration = acceleration;
    }
    public int getMpg() {
        return mpg;
    }

    public void setMpg(int mpg) {
        this.mpg = mpg;
    }

    public int getDisplacement() {
        return displacement;
    }

    public OriginsEnum getOrign() {
        return origin;
    }

    public void setOrign(OriginsEnum orign) {
        this.origin = orign;
    }

    public void setDisplacement(int displacement) {
        this.displacement = displacement;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    public String toString() {
        return "DataEntry{" +
                "mpg=" + mpg +
                ", displacement=" + displacement +
                ", horsepower=" + horsepower +
                ", weight=" + weight +
                ", acceleration=" + acceleration +
                ", origin=" + origin +
                '}';
    }


    //Had methode ela 9bal mes methode ykon dynamique, donc kan3tiha attribute ela chka enum o katraj3 liya valeur li gha baghi
    public float enumToAttribute(AttributesEnum attribute)
    {
        switch (attribute)
        {
            case MPG:
                return getMpg();
            case DISPLACEMENT:
                return getDisplacement();
            case HORSEPOWER:
                return getHorsepower();
            case WEIGHT:
                return getWeight();
            case ACCELERATION:
                return getAcceleration();
            default:
                return -1;

        }

    }

}
