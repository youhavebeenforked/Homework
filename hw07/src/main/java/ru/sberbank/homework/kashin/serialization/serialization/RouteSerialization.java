package ru.sberbank.homework.kashin.serialization.serialization;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class RouteSerialization<C extends SerializationCity> extends Route<C> implements Serializable {
    public RouteSerialization(String s, List<C> cities) {
        super(s, cities);
    }

    private void writeObject(ObjectOutputStream out)
            throws IOException {
        out.defaultWriteObject();
        out.writeObject(getRouteName());
        out.writeObject(getCities());
    }
    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        setRouteName((String)in.readObject());
        setCities((List<C>) in.readObject());
    }

    @Override
    public String toString() {
        return "Route: { " +
                String.join(" -> ", getCities().stream().map(SerializationCity::getCityName).collect(Collectors.toList()))
                + " }";
    }
}
