package com.jh.lebid.cache;

import com.jh.lebid.dto.Vacancy;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class VacancyCache {

    private Date date;

    private TreeSet<Vacancy> cache;

    public List<Vacancy> getNewestVacanciesFromCache(TreeSet<Vacancy> vacancies) {
        if (cache == null) {
            cache = vacancies;
            date = vacancies.last().getDate();
            return Collections.emptyList();
        } else {
            date = vacancies.last().getDate();
            cache.addAll(vacancies);
            return vacancies
                    .stream()
                    .filter(v -> v.getDate().after(date))
                    .collect(Collectors.toList());
        }
    }

}
