package com.mays.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.util.Map;
import java.util.stream.Collectors;

public class ResourceDataLocationReader<T> {
    private final BufferedReader reader;

    public ResourceDataLocationReader(BufferedReader reader) {
        this.reader = reader;
    }

    public <E extends T> Map<String, E> getGson() {
        String json = reader.lines().collect(Collectors.joining("\n"));
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<Map<String, E>>(){}.getType());
    }
}
