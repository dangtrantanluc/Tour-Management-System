package model;

import java.util.List;

public class TourRoute {
    private String id;
    private String name;
    //    private List<Destination> destinations;
//    private List<Transportation> transportations;
    private String destinations;
    private String transportations;

    public TourRoute(String id, String name, String destinations, String transportations) {
        this.id = id;
        this.name = name;
        this.destinations = destinations;
        this.transportations = transportations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestinations() {
        return destinations;
    }

    public void setDestinations(String destinations) {
        this.destinations = destinations;
    }

    public String getTransportations() {
        return transportations;
    }

    public void setTransportations(String transportations) {
        this.transportations = transportations;
    }

    @Override
    public String toString() {
        return "TourRoute{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", destinations=" + destinations +
                ", transportations=" + transportations +
                '}';
    }
}
