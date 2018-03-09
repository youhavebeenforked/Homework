package ru.sberbank.homework.andreev.serialization.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import ru.sberbank.homework.common.City;

import java.time.LocalDate;
import java.util.List;

public class CitySerializer extends Serializer<City> {

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
        City result = new City();
        result.setId(input.readInt());
        result.setCityName(input.readString());
        result.setFoundDate(kryo.readObject(input, LocalDate.class));
        result.setNumberOfInhabitants(input.readLong());
        int size = input.readInt();
        List<City> nearCities = result.getNearCities();
        for (int i = 0; i < size; i++) {
            City city = kryo.readObject(input, City.class);
            nearCities.add(city);
        }
        return result;
    }
}
