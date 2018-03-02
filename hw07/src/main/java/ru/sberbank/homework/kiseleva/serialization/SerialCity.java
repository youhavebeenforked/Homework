package ru.sberbank.homework.kiseleva.serialization;

import ru.sberbank.homework.common.City;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Ekaterina Kiseleva on 01.03.2018.
 */
public class SerialCity extends City implements Serializable {
    private int id;
    private String cityName;
    private LocalDate foundDate;
    private long numberOfInhabitants;

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
}
