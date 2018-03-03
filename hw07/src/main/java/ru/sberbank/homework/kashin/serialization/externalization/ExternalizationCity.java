package ru.sberbank.homework.kashin.serialization.externalization;

import ru.sberbank.homework.common.City;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExternalizationCity extends City implements Externalizable {
    private List<ExternalizationCity> nearCities;

    public ExternalizationCity() {
        setCityName("");
        setFoundDate(LocalDate.now());
        nearCities = new ArrayList<>();
    }

    public ExternalizationCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        this(id, cityName, foundDate, numberOfInhabitants, new ArrayList<>());
    }

    public ExternalizationCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants, List<ExternalizationCity> nearCities) {
        setId(id);
        setCityName(cityName);
        setFoundDate(foundDate);
        setNumberOfInhabitants(numberOfInhabitants);
        this.nearCities = nearCities;
    }

    public List getNearCities() {
        return nearCities;
    }

    public void setNearCities(List nearCities) {
        this.nearCities = nearCities;
    }

    public void addCity(City nextOne) {
        nearCities.add((ExternalizationCity) nextOne);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(getId());
        out.writeObject(getCityName());
        out.writeObject(getFoundDate());
        out.writeLong(getNumberOfInhabitants());
        out.writeObject(getNearCities());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId(in.readInt());
        setCityName((String) in.readObject());
        setFoundDate((LocalDate) in.readObject());
        setNumberOfInhabitants(in.readLong());
        setNearCities((List) in.readObject());
    }
}
