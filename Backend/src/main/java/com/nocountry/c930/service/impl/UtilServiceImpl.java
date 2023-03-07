package com.nocountry.c930.service.impl;

import com.nocountry.c930.service.IUtilService;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.concurrent.TimeUnit;

@Service
public class UtilServiceImpl implements IUtilService {
    @Override
    public String makePaginationLink(HttpServletRequest request, int page) {
        return String.format("%s?page=%d", request.getRequestURI(), page);
    }

    @Override
    public int calculateDaysLeft(Date date) {

        Long todayMillis = System.currentTimeMillis();
        Long closingDateMillis = date.getTime();

        Long timeDiff = Math.abs(closingDateMillis - todayMillis);

        Long daysLeft = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);


        return daysLeft.intValue();
    }
}
