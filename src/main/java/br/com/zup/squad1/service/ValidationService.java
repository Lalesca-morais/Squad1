package br.com.zup.squad1.service;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class ValidationService {
    public boolean isValidDateFormat(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            dateFormat.parse(date);
            return false;
        } catch (ParseException e) {
            return true;
        }
    }

    public boolean isValidYearMonthFormat(String yearMonth) {
        try {
            SimpleDateFormat yearMonthFormat = new SimpleDateFormat("MM/yyyy");
            yearMonthFormat.setLenient(false);
            yearMonthFormat.parse(yearMonth);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
