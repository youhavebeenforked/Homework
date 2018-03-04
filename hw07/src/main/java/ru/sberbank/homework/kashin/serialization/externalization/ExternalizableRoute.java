package ru.sberbank.homework.kashin.serialization.externalization;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class ExternalizableRoute<C> extends Route<City> implements Externalizable {
    ExternalizableRoute(String s, List<City> cities) {
        super(s, cities);
    }

    public ExternalizableRoute() {
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getRouteName());
        out.writeObject(getCities());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
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