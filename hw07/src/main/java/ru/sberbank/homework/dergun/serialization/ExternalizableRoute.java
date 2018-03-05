package ru.sberbank.homework.dergun.serialization;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

public class ExternalizableRoute extends Route<City> implements Externalizable {
    public ExternalizableRoute() {
        super();
    }
    public ExternalizableRoute(String s, List<City> cities) {
        super(s, cities);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getRouteName());
        out.writeInt(getCities().size());
        for (City city : getCities()) {
            ((Externalizable)city).writeExternal(out);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setRouteName((String) in.readObject());
        List<City> cities = new ArrayList<>();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            ExternalizableCity externalizableCity = new ExternalizableCity();
            externalizableCity.readExternal(in);
            cities.add(externalizableCity);
        }
        setCities(cities);
    }
}
