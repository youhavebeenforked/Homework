package ru.sberbank.homework.koval.serialization.serializable;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.*;
import java.util.List;

public class SerializableRoute extends Route<City> implements Serializable {
    public SerializableRoute() {
        super();
    }

    public SerializableRoute(String routeName, List<City> cities) {
        super(routeName, cities);
    }

    private void writeObject(ObjectOutputStream out)
            throws IOException {
        out.defaultWriteObject();

        out.writeUTF(super.getRouteName());
        out.writeObject(super.getCities());
    }

    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        super.setRouteName(in.readUTF());
        super.setCities((List<City>) in.readObject());
    }

}
