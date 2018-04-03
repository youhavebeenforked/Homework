package ru.sberbank.homework.koval.serialization.externalizable;


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
    public void writeExternal(ObjectOutput out)
            throws IOException {

        out.writeUTF(super.getRouteName());
        out.writeObject(super.getCities());
    }

    @Override
    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {

        super.setRouteName(in.readUTF());
        super.setCities((List<City>) in.readObject());
    }
}
