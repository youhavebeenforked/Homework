package ru.sberbank.homework.bobrov.serialization;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RouteWrapperExt<C extends City> extends Route implements Externalizable {
    private List<C> cities = new LinkedList<>();


    public RouteWrapperExt() {
    }

    public RouteWrapperExt(List<C> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "Route: { " +
                String.join(" -> ", cities.stream().map(City::getCityName).collect(Collectors.toList()))
                + " }";
    }

    public List<C> getCities() {
        return cities;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(cities);
        out.writeObject(this.toString());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.cities = (List<C>) in.readObject();
        String route = (String) in.readObject();
    }

}
