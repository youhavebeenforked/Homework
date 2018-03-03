package ru.sberbank.homework.kashin.serialization.serialization;

import ru.sberbank.homework.common.City;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SerializationCity extends City implements Serializable {
    private List<SerializationCity> nearCities;

    public SerializationCity() {
        setCityName("");
        setFoundDate(LocalDate.now());
        nearCities = new ArrayList<>();
    }

    public SerializationCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        this(id, cityName, foundDate, numberOfInhabitants, new ArrayList<>());
    }

    public SerializationCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants, List<SerializationCity> nearCities) {
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
        nearCities.add((SerializationCity) nextOne);
    }


    public void write(ObjectOutput out) throws IOException {
        out.writeInt(getId());
        out.writeObject(getCityName());
        out.writeObject(getFoundDate());
        out.writeLong(getNumberOfInhabitants());
        out.writeObject(getNearCities());
    }


    public void read(ObjectInput in) throws IOException, ClassNotFoundException {
        setId(in.readInt());
        setCityName((String) in.readObject());
        setFoundDate((LocalDate) in.readObject());
        setNumberOfInhabitants(in.readLong());
        setNearCities((List) in.readObject());
    }
}
