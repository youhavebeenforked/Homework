package ru.sberbank.homework.kashin.serialization.externalization;

import ru.sberbank.homework.common.Route;

import java.io.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RouteExternalization<C extends ExternalizationCity> extends Route<C> implements Externalizable {
    public RouteExternalization(String s, List<C> cities) {
        super(s, cities);
    }

    public RouteExternalization() {
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getRouteName());
        out.writeInt(getCities().size());
        for (C c : getCities()) {
            out.writeInt(c.getId());
            out.writeObject(c.getCityName());
            out.writeObject(c.getFoundDate());
            out.writeLong(c.getNumberOfInhabitants());
            out.writeObject(c.getNearCities());
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setRouteName((String) in.readObject());
        int size = in.readInt();
        setCities(new LinkedList<>());
        for (int i = 0; i < size; i++) {
            getCities().add((C) new ExternalizationCity());
        }
        for (int i = 0; i < size; i++) {
            getCities().get(i).setId(in.readInt());
            getCities().get(i).setCityName((String) in.readObject());
            getCities().get(i).setFoundDate((LocalDate) in.readObject());
            getCities().get(i).setNumberOfInhabitants(in.readLong());
            getCities().get(i).setNearCities((List) in.readObject());
        }
    }

    @Override
    public String toString() {
        return "Route: { " +
                String.join(" -> ", getCities().stream().map(ExternalizationCity::getCityName).collect(Collectors.toList()))
                + " }";
    }
}