package model;

import java.util.ArrayList;
import java.util.List;

public class TourRouteManager {
    private List<TourRoute> tourRoutes;

    public TourRouteManager() {
        tourRoutes = new ArrayList<>();
    }

    public void addTourRoute(TourRoute tourRoute) {
        tourRoutes.add(tourRoute);
    }

    public void updateTourRoute(String routeId, String newName) {
        for (TourRoute route : tourRoutes) {
            if (route.getId().equals(routeId)) {
                route = new TourRoute(routeId, newName, route.getDestinations(), route.getTransportations());
            }
        }
    }

    public void deleteTourRoute(String routeId) {
        tourRoutes.removeIf(route -> route.getId().equals(routeId));
    }

    public List<TourRoute> getAllTourRoutes() {
        return tourRoutes;
    }

    public TourRoute findById(String id) {
        return this.tourRoutes.stream().filter(tourRoute -> tourRoute.getId().equals(id)).findFirst().orElse(null);
    }
}

