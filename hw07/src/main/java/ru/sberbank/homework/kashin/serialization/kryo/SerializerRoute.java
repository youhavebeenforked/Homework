package ru.sberbank.homework.kashin.serialization.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.util.ArrayList;

public class SerializerRoute extends Serializer<Route> {

    @Override
    public void write(Kryo kryo, Output output, Route route) {
        kryo.writeObject(output, route.getRouteName());
        output.write(route.getCities().size());
        route.getCities().forEach(city -> kryo.writeObject(output, city));
    }

    @Override
    public Route read(Kryo kryo, Input input, Class<Route> type) {
        Route<City> route = kryo.newInstance(type);
        route.setRouteName(kryo.readObject(input, String.class));
        int size = input.read();
        for (int i = 0; i < size; i++) {
            route.getCities().add(kryo.readObject(input, City.class));
        }
        return route;
    }
}
