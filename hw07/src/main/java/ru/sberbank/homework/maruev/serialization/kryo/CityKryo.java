package ru.sberbank.homework.maruev.serialization.kryo;

/**
 * Created by Иван on 13.03.2018.
 */

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import ru.sberbank.homework.common.City;

import java.time.LocalDate;
import java.util.ArrayList;

public class CityKryo extends Serializer<City> {

    @Override
    public void write(Kryo kryo, Output output, City city) {
        output.writeInt(city.getId());
        output.writeString(city.getCityName());
        output.writeLong(city.getFoundDate().toEpochDay());
        output.writeLong(city.getNumberOfInhabitants());

        kryo.writeObject(output, city.getNearCities());
    }

    @Override
    public City read(Kryo kryo, Input input, Class<City> type) {
        City city = new City();

        kryo.reference(city);
        city.setId(input.readInt());
        city.setCityName(input.readString());
        city.setFoundDate(LocalDate.ofEpochDay(input.readLong()));
        city.setNumberOfInhabitants(input.readLong());
        city.setNearCities(kryo.readObject(input, ArrayList.class));
        return city;
    }
}
