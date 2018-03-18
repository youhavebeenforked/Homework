package ru.sberbank.homework.karaush.serialization.serializable;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class SerializableRoute extends Route<City> implements Serializable {

    public SerializableRoute(String routeName, List<City> cities) {
        super(routeName, cities);
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(getRouteName());
        oos.writeObject(getCities());
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        setRouteName((String) ois.readObject());
        setCities((List<City>) ois.readObject());
    }

}