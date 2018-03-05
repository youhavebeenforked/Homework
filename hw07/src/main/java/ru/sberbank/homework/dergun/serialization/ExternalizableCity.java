package ru.sberbank.homework.dergun.serialization;

import ru.sberbank.homework.common.City;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;

public class ExternalizableCity extends City implements Externalizable {
    public ExternalizableCity(City city) {
        super(city.getId(), city.getCityName(), city.getFoundDate(), city.getNumberOfInhabitants(), city.getNearCities());
    }

    public ExternalizableCity() {
        super();
    }

    private void writeExternal(ObjectOutput out, City city, HashSet<Integer> visited) throws IOException {
        out.writeInt(city.getId());
        if (visited.contains(city.getId())) {
            return;
        }
        visited.add(city.getId());
        out.writeObject(city.getCityName());
        out.writeObject(city.getFoundDate());
        out.writeLong(city.getNumberOfInhabitants());
        out.writeInt(city.getNearCities().size());
        for (City near : city.getNearCities()) {
            writeExternal(out, near, visited);
        }
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        writeExternal(out, this, new HashSet<>());
    }

    private void readExternal(ObjectInput in, City city, HashMap<Integer, City> visited) throws IOException, ClassNotFoundException {
        visited.put(city.getId(), city);
        city.setCityName((String) in.readObject());
        city.setFoundDate((LocalDate) in.readObject());
        city.setNumberOfInhabitants(in.readLong());
        int sizeNear = in.readInt();
        for (int i = 0; i < sizeNear; i++) {
            int id = in.readInt();
            if (visited.containsKey(id)) {
                city.getNearCities().add(visited.get(id));
                continue;
            }
            City cityNear = new City();
            cityNear.setId(id);
            readExternal(in, cityNear, visited);
            city.getNearCities().add(cityNear);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId(in.readInt());
        readExternal(in, this, new HashMap<>());
    }
}
