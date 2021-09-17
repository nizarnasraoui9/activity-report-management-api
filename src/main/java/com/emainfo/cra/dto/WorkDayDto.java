package com.emainfo.cra.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkDayDto {
    private int dayOfMonth;
    private String dayName ;
    private String workedTime;
    private int workedSeconds;
}