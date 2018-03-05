package ru.sberbank.homework.dergun.serialization;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class SerializableRoute extends Route<City> implements Serializable {
    private final String name;
    private final List<City> cities;
    public SerializableRoute(Route<City> route) {
        name = route.getRouteName();
        cities = route.getCities();
    }

    public Route<City> getRoute() {
        return new Route<>(name, cities);
    }
}