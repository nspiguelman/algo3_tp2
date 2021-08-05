package edu.fiuba.algo3.helper;

import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Helper {
    public static JsonReader crearJsonReader(String path) throws FileNotFoundException {
        return new JsonReader(new FileReader(path));
    }


}