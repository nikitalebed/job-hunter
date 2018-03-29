package com.jh.lebid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Vacancy implements Comparable<Vacancy> {

    private String companyName;
    private String description;
    private Integer salary;
    private String cityName;
    private String contactURL;
    private Date date;

    @Override
    public String toString() {
        return "company name: " + companyName + System.lineSeparator() +
                "description: " + description + System.lineSeparator() +
                "salary: " + salary + System.lineSeparator() +
                "cityName: " + companyName + System.lineSeparator() +
                "contactURL: " + contactURL + System.lineSeparator() +
                "date: " + date + System.lineSeparator();
    }

    @Override
    public int compareTo(Vacancy o) {
        if (date.after(o.getDate())) {
            return 1;
        } else if (date.before(o.getDate())) {
            return -1;
        } else {
            return 0;
        }
    }
}
