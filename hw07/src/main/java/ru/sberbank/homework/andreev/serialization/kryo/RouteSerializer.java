package ru.sberbank.homework.andreev.serialization.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.util.List;

public class RouteSerializer extends Serializer<Route<City>> {

    @Override
    public void write(Kryo kryo, Output output, Route<City> route) {
        output.writeString(route.getRouteName());
        List<City> cities = route.getCities();
        output.writeInt(cities.size());
        for (City city : cities) {
            kryo.writeObject(output, city);
        }
    }

    @Override
    public Route<City> read(Kryo kryo, Input input, Class<Route<City>> type) {
        Route<City> result = new Route<>();
        result.setRouteName(input.readString());
        int size = input.readInt();
        List<City> cities = result.getCities();
        for (int i = 0; i < size; i++) {
            cities.add(kryo.readObject(input, City.class));
        }
        return result;
    }
}
