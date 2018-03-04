package ru.sberbank.homework.kashin.serialization.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import ru.sberbank.homework.common.City;

import java.time.LocalDate;
import java.util.ArrayList;

public class SerializerCity extends Serializer<City> {

    @Override
    public void write(Kryo kryo, Output output, City city) {
        output.write(city.getId());
        kryo.writeObject(output, city.getCityName());
        kryo.writeObject(output, city.getFoundDate());
        kryo.writeObject(output, city.getNumberOfInhabitants());
        kryo.writeObject(output, city.getNearCities());
    }

    @Override
    public City read(Kryo kryo, Input input, Class<City> type) {
        City city = kryo.newInstance(type);
        kryo.reference(city);
        city.setId(input.read());
        city.setCityName(kryo.readObject(input, String.class));
        city.setFoundDate(kryo.readObject(input, LocalDate.class));
        city.setNumberOfInhabitants(kryo.readObject(input, Long.class));
        city.setNearCities(kryo.readObject(input, ArrayList.class));
        return city;
    }
}
