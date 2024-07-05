package com.mays.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ResourceDataLocation {
    private final String namespace;
    private final String path;
    private final String FULLPATH;

    public ResourceDataLocation(String namespace, String path) {
        this.namespace = namespace;
        this.path = "/data/" + namespace + "/" + path;
        this.FULLPATH = this.path;
    }

    public ResourceDataLocationReader read() {
        try (InputStream inputStream = getClass().getResourceAsStream(FULLPATH)) {
            if (inputStream == null) {
                throw new RuntimeException("Resource not found: " + FULLPATH);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            return new ResourceDataLocationReader<>(reader);
        } catch (IOException e) {
            throw new RuntimeException("Error reading resource: " + FULLPATH, e);
        }
    }
}
