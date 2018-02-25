package ru.sberbank.homework.kashin.serialization.serialization;

import ru.sberbank.homework.common.City;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SerializationCity extends City implements Serializable {
    private int id;
    private String cityName;
    private LocalDate foundDate;
    private long numberOfInhabitants;
    private List<SerializationCity> nearCities;

    public SerializationCity() {
        cityName = "";
        foundDate = LocalDate.now();
        nearCities = new ArrayList<>();
    }

    public SerializationCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        this(id, cityName, foundDate, numberOfInhabitants, new ArrayList<>());
    }

    public SerializationCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants, List<SerializationCity> nearCities) {
        this.id = id;
        this.cityName = cityName;
        this.foundDate = foundDate;
        this.numberOfInhabitants = numberOfInhabitants;
        this.nearCities = nearCities;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getCityName() {
        return cityName;
    }

    @Override
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public LocalDate getFoundDate() {
        return foundDate;
    }

    @Override
    public void setFoundDate(LocalDate foundDate) {
        this.foundDate = foundDate;
    }

    @Override
    public long getNumberOfInhabitants() {
        return numberOfInhabitants;
    }

    @Override
    public void setNumberOfInhabitants(long numberOfInhabitants) {
        this.numberOfInhabitants = numberOfInhabitants;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SerializationCity city = (SerializationCity) o;
        return id == city.id &&
                Objects.equals(cityName, city.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cityName);
    }

    public boolean compare(SerializationCity city) {
        return id == city.id &&
                numberOfInhabitants == city.numberOfInhabitants &&
                Objects.equals(cityName, city.cityName) &&
                Objects.equals(foundDate, city.foundDate) &&
                Objects.equals(nearCities, city.nearCities);
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", foundDate=" + foundDate +
                ", numberOfInhabitants=" + numberOfInhabitants +
                // ", nearCities=" + nearCities +
                '}';
    }
}
