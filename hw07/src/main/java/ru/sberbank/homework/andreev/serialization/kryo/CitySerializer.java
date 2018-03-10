package ru.sberbank.homework.andreev.serialization.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import ru.sberbank.homework.common.City;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CitySerializer extends Serializer<City> {
    private List<City> cachedCities = new ArrayList<>();

    @Override
    public void write(Kryo kryo, Output output, City city) {
        output.writeInt(city.getId());
        output.writeString(city.getCityName());
        kryo.writeObject(output, city.getFoundDate());
        output.writeLong(city.getNumberOfInhabitants());
        List<City> nearCities = city.getNearCities();
        output.writeInt(nearCities.size());
        for (City nearCity : nearCities) {
            kryo.writeObject(output, nearCity);
        }
    }

    @Override
    public City read(Kryo kryo, Input input, Class<City> type) {
        City readingCity = new City();
        readingCity.setId(input.readInt());
        readingCity.setCityName(input.readString());
        readingCity.setFoundDate(kryo.readObject(input, LocalDate.class));
        readingCity.setNumberOfInhabitants(input.readLong());

        Optional<City> cached = cachedCities.stream()
                .filter(e -> ((e.getId() == readingCity.getId())
                        && (e.getCityName().equals(readingCity.getCityName()))
                        && (e.getNumberOfInhabitants() == readingCity.getNumberOfInhabitants())))
                .findFirst();
        City result;
        if(cached.isPresent()){
            result = cached.get();
        }else {
            result = readingCity;
            cachedCities.add(result);
        }

        int size = input.readInt();
        List<City> nearCities = result.getNearCities();
        for (int i = 0; i < size; i++) {
            City city = kryo.readObject(input, City.class);
            nearCities.add(city);
        }
        return result;
    }
}
