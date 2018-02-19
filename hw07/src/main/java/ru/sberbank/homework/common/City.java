package ru.sberbank.homework.common;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class City {
    private int id;
    private String cityName;
    private LocalDate foundDate;
    private long numberOfInhabitants;
    private List<City> nearCities = new ArrayList<>();

    public City() {
    }

    public City(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        this.id = id;
        this.cityName = cityName;
        this.foundDate = foundDate;
        this.numberOfInhabitants = numberOfInhabitants;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public LocalDate getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(LocalDate foundDate) {
        this.foundDate = foundDate;
    }

    public long getNumberOfInhabitants() {
        return numberOfInhabitants;
    }

    public void setNumberOfInhabitants(long numberOfInhabitants) {
        this.numberOfInhabitants = numberOfInhabitants;
    }

    public List<City> getNearCities() {
        return nearCities;
    }

    public void setNearCities(List<City> nearCities) {
        this.nearCities = nearCities;
    }

    public void addCity(City nextOne) {
        nearCities.add(nextOne);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id &&
                Objects.equals(cityName, city.cityName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, cityName);
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                '}';
    }
}
