package ru.sberbank.homework.kiseleva.serialization;

import ru.sberbank.homework.common.City;

import java.io.*;
import java.time.LocalDate;

/**
 * Created by Ekaterina Kiseleva on 01.03.2018.
 */
public class SerialCity extends City implements Serializable, Externalizable {
    private int id;
    private String cityName;
    private LocalDate foundDate;
    private long numberOfInhabitants;

    public SerialCity() {

    }

    public SerialCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        this.id = id;
        this.cityName = cityName;
        this.foundDate = foundDate;
        this.numberOfInhabitants = numberOfInhabitants;
    }

    @Override
    public String getCityName() {
        return cityName;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeObject(cityName);
        out.writeObject(foundDate);
        out.writeObject(numberOfInhabitants);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = (int) in.readObject();
        this.cityName = (String) in.readObject();
        this.foundDate = (LocalDate) in.readObject();
        this.numberOfInhabitants = (long) in.readObject();
    }
}
