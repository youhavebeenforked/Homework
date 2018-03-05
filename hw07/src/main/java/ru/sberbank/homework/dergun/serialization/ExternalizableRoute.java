package ru.sberbank.homework.dergun.serialization;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

public class ExternalizableRoute extends Route<City> implements Externalizable {

    private List<City> cities;
    private String routeName;

    public ExternalizableRoute(Route<City> route) {
        cities = route.getCities();
        routeName = route.getRouteName();
    }

    public ExternalizableRoute() {
        cities = new ArrayList<>();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(routeName);
        out.writeInt(cities.size());
        for (City city : cities) {
            new ExternalizableCity(city).writeExternal(out);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        routeName = (String) in.readObject();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            ExternalizableCity externalizableCity = new ExternalizableCity();
            externalizableCity.readExternal(in);
            cities.add(externalizableCity);
        }
    }

    Route<City> getRoute() {
        return new Route<>(routeName, cities);
    }
}
