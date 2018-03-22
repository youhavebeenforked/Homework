package ru.sberbank.homework.karaush.serialization.externalizable;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

public class ExternalizableRoute extends Route<City> implements Externalizable {

    public ExternalizableRoute() {
        super();
    }

    public ExternalizableRoute(String routeName, List<City> cities) {
        super(routeName, cities);
    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeChars(super.getRouteName());
        List<City> cities = super.getCities();
        out.writeInt(cities.size());
        for (City city : cities) {
            out.writeObject(city);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.setRouteName(in.readLine());
        List<City> cities = super.getCities();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            cities.add((ExternalizableCity) in.readObject());
        }
    }
}
