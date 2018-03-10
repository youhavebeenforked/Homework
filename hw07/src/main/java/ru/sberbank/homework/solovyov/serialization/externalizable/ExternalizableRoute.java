package ru.sberbank.homework.solovyov.serialization.externalizable;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ExternalizableRoute<C extends City> extends Route<C> implements Externalizable {
    public ExternalizableRoute(String routeName, List<C> cities) {
        super(routeName, cities);
    }

    public ExternalizableRoute() {
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getRouteName());
        out.writeObject(getCities());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setRouteName((String) in.readObject());
        setCities((List<C>) in.readObject());
    }
}
