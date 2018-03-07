package ru.sberbank.homework.kiseleva.serialization;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ekaterina Kiseleva on 01.03.2018.
 */
public class SerialRoute<C extends SerialCity> extends Route implements Serializable, Externalizable {
    private String routeName;
    private List<City> cities = new LinkedList<>();

    public SerialRoute() {

    }

    public SerialRoute(String routeName, List<City> cities) {
        this.routeName = routeName;
        this.cities = cities;
    }

    public String toString() {
        return "Route: { " +
                String.join(" -> ", cities.stream().map(City::getCityName).collect(Collectors.toList()))
                + " }";
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(routeName);
        out.writeObject(cities.size());
        for (City city : cities) {
            new SerialCity(city.getId(), city.getCityName(), city.getFoundDate(), city.getNumberOfInhabitants())
                    .writeExternal(out);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        routeName = (String) in.readObject();
        int size = (int) in.readObject();
        for (int i = 0; i < size; i++) {
            SerialCity city = new SerialCity();
            city.readExternal(in);
            cities.add(city);
        }
    }
}
