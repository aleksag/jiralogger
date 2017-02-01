package no.nhst.daily;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.nhst.daily.model.Activity;
import no.nhst.daily.model.Day;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by alex on 31/01/2017.
 */
public class Parser {

    public List<Day> parseFile(String path){
        ObjectMapper mapper = new ObjectMapper();
        try {
            Day[] days = mapper.readValue(new File(path), Day[].class);
            return Arrays.asList(days);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
