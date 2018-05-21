package lesson05.exercisecollectionsandmaps;

public class Capital {
    private String name;
    private int population;
    private float squareMileage;

    public Capital(String name, int population, float squareMileage) {
        this.name = name;
        this.population = population;
        this.squareMileage = squareMileage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public float getSquareMileage() {
        return squareMileage;
    }

    public void setSquareMileage(float squareMileage) {
        this.squareMileage = squareMileage;
    }
}
