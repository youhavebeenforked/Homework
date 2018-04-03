package ru.sberbank.homework.maruev.serialization.serializable;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Иван on 13.03.2018.
 */
public class SerialRoute<C extends City> extends Route<C> implements Serializable {
    private List<C> cities = new LinkedList<>();

    public SerialRoute(String routeName, List<C> cities) {
        super(routeName, cities);
        this.cities = cities;
    }

    @Override
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
