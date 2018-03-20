package ru.sberbank.homework.bobrov.serialization.serial;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RouteWrapperSer<C extends City> extends Route implements Serializable {
    private List<C> cities = new LinkedList<>();

    public RouteWrapperSer(List<C> cities) {
        this.cities = cities;
    }

    public List<C> getCities() {
        return cities;
    }

    @Override
    public String toString() {
        return "Route: { " +
                String.join(" -> ", cities.stream().map(City::getCityName).collect(Collectors.toList()))
                + " }";
    }
}