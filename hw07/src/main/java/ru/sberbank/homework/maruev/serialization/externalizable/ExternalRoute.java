package ru.sberbank.homework.maruev.serialization.externalizable;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Иван on 14.03.2018.
 */
public class ExternalRoute extends Route<City> implements Externalizable {

    public ExternalRoute() {
    }

    public ExternalRoute(String routeName, List<City> cities) {
        super(routeName, cities);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(super.getRouteName());
        out.writeObject(super.getCities().size());

        for (City city : super.getCities()) {
            out.writeObject(city.getId());
            out.writeObject(city.getCityName());
            out.writeObject(city.getFoundDate());
            out.writeObject(city.getNumberOfInhabitants());
            out.writeObject(city.getNearCities());
        }

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setRouteName((String) in.readObject());
        int size = (int) in.readObject();

        for (int i = 0; i < size; i++) {
            City city = new City();
            city.setId((int) in.readObject());
            city.setCityName((String) in.readObject());
            city.setFoundDate((LocalDate) in.readObject());
            city.setNumberOfInhabitants((long) in.readObject());
            city.setNearCities((List<City>) in.readObject());
            super.getCities().add(city);
        }

    }

    @Override
    public String toString() {
        return "Route: { " +
                String.join(" -> ", super.getCities().stream().map(City::getCityName).collect(Collectors.toList()))
                + " }";
    }
}
