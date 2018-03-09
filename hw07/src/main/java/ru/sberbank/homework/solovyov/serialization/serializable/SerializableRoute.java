package ru.sberbank.homework.solovyov.serialization.serializable;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class SerializableRoute<C extends City> extends Route<C> implements Serializable {
    public SerializableRoute(String routeName, List<C> cities) {
        super(routeName, cities);
    }

    private void writeObject(ObjectOutputStream oos)
            throws IOException {
        oos.writeObject(getRouteName());
        oos.writeObject(getCities());
    }

    private void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
        setRouteName((String) ois.readObject());
        setCities((List<C>) ois.readObject());
    }
}
