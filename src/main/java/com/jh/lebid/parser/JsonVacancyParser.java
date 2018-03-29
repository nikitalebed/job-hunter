package com.jh.lebid.parser;

import com.jh.lebid.dto.Vacancy;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonVacancyParser {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @SneakyThrows
    public List<Vacancy> parseJsonToList(JSONArray vacancyJsonArray) {
        List<Vacancy> result = new ArrayList<>();
        for (int i = 1; i < vacancyJsonArray.toList().size(); i++) {
            JSONObject jsonVacancy = vacancyJsonArray.getJSONObject(i);
            result.add(new Vacancy(
                    String.valueOf(jsonVacancy.get("companyName")),
                    String.valueOf(jsonVacancy.get("description")),
                    Integer.parseInt(String.valueOf(jsonVacancy.get("salary"))),
                    String.valueOf(jsonVacancy.get("cityName")),
                    String.valueOf(jsonVacancy.get("contactURL")),
                    format.parse(String.valueOf(jsonVacancy.get("date")))
            ));
        }
        return result;
    }

}
