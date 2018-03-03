package ru.sberbank.homework.kashin.serialization.serialization;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RouteSerialization<C> extends Route<City> implements Serializable {
    public RouteSerialization(String s, List<City> cities) {
        super(s, cities);
    }

    private void writeObject(ObjectOutputStream out)
            throws IOException {
        out.writeObject(getRouteName());
        out.writeObject(getCities());
    }

    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        setRouteName((String) in.readObject());
        setCities((List<City>) in.readObject());
    }

    @Override
    public String toString() {
        return "Route: { " +
                String.join(" -> ", getCities().stream().map(City::getCityName).collect(Collectors.toList()))
                + " }";
    }
}
