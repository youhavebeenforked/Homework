package ru.sberbank.homework.kashin.serialization;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RouteExternalization<C extends City> extends Route implements Externalizable {
    public RouteExternalization(String s, List<C> cities) {
        super(s, cities);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getRouteName());
        out.writeObject(getCities());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setRouteName((String) in.readObject());
        setCities((LinkedList<C>)in.readObject());
    }
}