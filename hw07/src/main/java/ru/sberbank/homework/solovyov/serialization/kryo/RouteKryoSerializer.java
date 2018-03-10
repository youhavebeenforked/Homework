package ru.sberbank.homework.solovyov.serialization.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.util.LinkedList;

public class RouteKryoSerializer extends Serializer<Route> {

    @Override
    public void write(Kryo kryo, Output output, Route route) {
        kryo.writeObject(output, route.getRouteName());
        kryo.writeObject(output, route.getCities());
    }

    @Override
    public Route read(Kryo kryo, Input input, Class<Route> type) {
        Route<City> route = new Route<>();

        route.setRouteName(kryo.readObject(input, String.class));
        route.setCities(kryo.readObject(input, LinkedList.class));

        return route;
    }
}
