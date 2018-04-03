package ru.sberbank.homework.bobrov.serialization.external;


import ru.sberbank.homework.common.City;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
import java.util.List;

/**
 * TODO: comment
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 19.03.2018
 */


public class CityWrapperExt extends City implements Externalizable {
    public CityWrapperExt() {
        super();
    }

    public CityWrapperExt(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        super(id, cityName, foundDate, numberOfInhabitants);
    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(super.getId());
        out.writeObject(super.getCityName());
        out.writeObject(super.getFoundDate());
        out.writeLong(super.getNumberOfInhabitants());
        out.writeObject(super.getNearCities());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.setId(in.readInt());
        super.setCityName((String) in.readObject());
        super.setFoundDate((LocalDate) in.readObject());
        super.setNumberOfInhabitants(in.readLong());
        super.setNearCities((List<City>) in.readObject());
    }
}
