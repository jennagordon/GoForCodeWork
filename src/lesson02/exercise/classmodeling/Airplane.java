package lesson02.exercise.classmodeling;

public class Airplane {
    private double altitude;
   // private Schedule schedule;
    private double longitude;
    private double latitude;
    private String origin;
    private String destination;
    private String planeNumber;

    public Airplane (String planeNumber) {
        this.planeNumber = planeNumber;
    }


    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
    /*
    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
    */

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPlaneNumber() {
        return planeNumber;
    }

    public void setPlaneNumber(String planeNumber) {
        this.planeNumber = planeNumber;
    }
}
