package com.nocountry.c930.service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public interface IUtilService {

    String makePaginationLink(HttpServletRequest request, int page);
    int calculateDaysLeft(Date date);
}
