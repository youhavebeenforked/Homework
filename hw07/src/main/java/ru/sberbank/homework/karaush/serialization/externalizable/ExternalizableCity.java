package ru.sberbank.homework.karaush.serialization.externalizable;

import ru.sberbank.homework.common.City;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
import java.util.List;

public class ExternalizableCity extends City implements Externalizable {

    public ExternalizableCity() {
        super();
    }

    public ExternalizableCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        super(id, cityName, foundDate, numberOfInhabitants);
    }

    public ExternalizableCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants, List<City> nearCities) {
        super(id, cityName, foundDate, numberOfInhabitants, nearCities);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(super.getId());
        out.writeChars(super.getCityName());
        out.writeObject(super.getFoundDate());
        out.writeLong(super.getNumberOfInhabitants());
        List<City> nearCities = super.getNearCities();
        out.writeInt(nearCities.size());
        for (City nearCity : nearCities) {
            out.writeObject(nearCity);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.setId(in.readInt());
        super.setCityName(in.readLine());
        super.setFoundDate((LocalDate) in.readObject());
        super.setNumberOfInhabitants(in.readLong());
        List<City> nearCities = super.getNearCities();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            nearCities.add((ExternalizableCity) in.readObject());
        }
    }
}
