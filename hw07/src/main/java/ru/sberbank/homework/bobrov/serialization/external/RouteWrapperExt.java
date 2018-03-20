package ru.sberbank.homework.bobrov.serialization.external;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.*;
import java.util.List;

public class RouteWrapperExt<C extends City> extends Route implements Externalizable {
    public RouteWrapperExt() {
        super();
    }

    public RouteWrapperExt(String routeName, List<City> cities) {
        super(routeName, cities);
    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(super.getCities());
        out.writeObject(super.getRouteName());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.setCities((List<City>) in.readObject());
        super.setRouteName((String) in.readObject());
    }

}
