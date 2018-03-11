package ru.sberbank.homework.andreev.serialization.internal;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class InternalRoute extends Route<City> implements Serializable{

    public InternalRoute(String routeName, List<City> cities) {
        super(routeName, cities);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeUTF(super.getRouteName());
        out.writeObject(super.getCities());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        super.setRouteName(in.readUTF());
        super.setCities((List<City>) in.readObject());
    }

}
