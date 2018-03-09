package ru.sberbank.homework.andreev.serialization.external;

import ru.sberbank.homework.common.City;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
import java.util.List;

public class ExternalCity extends City implements Externalizable {

    public ExternalCity() {
        super();
    }

    public ExternalCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        super(id, cityName, foundDate, numberOfInhabitants);
    }

    public ExternalCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants, List<City> nearCities) {
        super(id, cityName, foundDate, numberOfInhabitants, nearCities);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(super.getId());
        out.writeUTF(super.getCityName());
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
        super.setCityName(in.readUTF());
        super.setFoundDate((LocalDate) in.readObject());
        super.setNumberOfInhabitants(in.readLong());
        List<City> nearCities = super.getNearCities();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            nearCities.add((ExternalCity) in.readObject());
        }
    }
}
