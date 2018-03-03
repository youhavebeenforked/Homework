package ru.sberbank.homework.kashin.serialization.serialization;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RouteSerialization<C extends SerializationCity> extends Route<C> implements Serializable {
    public RouteSerialization(String s, List<C> cities) {
        super(s, cities);
    }

    private void writeObject(ObjectOutputStream out)
            throws IOException {
        out.writeObject(getRouteName());
        out.writeInt(getCities().size());
        for (C c : getCities()) {
            c.write(out);
        }
    }

    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        setRouteName((String) in.readObject());
        int size = in.readInt();
        setCities(new LinkedList<>());
        for (int i = 0; i < size; i++) {
            getCities().add((C) new SerializationCity());
        }
        for (int i = 0; i < size; i++) {
            getCities().get(i).read(in);
        }
    }

    @Override
    public String toString() {
        return "Route: { " +
                String.join(" -> ", getCities().stream().map(SerializationCity::getCityName).collect(Collectors.toList()))
                + " }";
    }
}
