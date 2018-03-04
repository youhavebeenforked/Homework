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
        output.writeInt(city.getId());
        output.writeString(city.getCityName());
        output.writeInt(city.getFoundDate().getYear());
        output.writeInt(city.getFoundDate().getDayOfYear());
        output.writeLong(city.getNumberOfInhabitants());
        kryo.writeObject(output, city.getNearCities());
    }

    @Override
    public City read(Kryo kryo, Input input, Class<City> type) {
        City city = kryo.newInstance(type);
        kryo.reference(city);
        city.setId(input.readInt());
        city.setCityName(input.readString());
        LocalDate localDate = LocalDate.ofYearDay(input.readInt(), input.readInt());
        city.setFoundDate(localDate);
        city.setNumberOfInhabitants(input.readLong());
        city.setNearCities(kryo.readObject(input, ArrayList.class));
        return city;
    }
}
