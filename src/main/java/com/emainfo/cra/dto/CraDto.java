package com.emainfo.cra.dto;

import java.util.List;

import com.emainfo.cra.model.Client;
import com.emainfo.cra.model.Contracted;
import com.emainfo.cra.model.WorkDay;

import lombok.AllArgsConstructor;
import lombok.Data;
import reactor.core.publisher.Mono;

@Data
@AllArgsConstructor
public class CraDto {
    private long id;
    private String type;
    private Integer year;
    private Integer month;
    private String note;
    private List<WorkDay> workDays;
    private String createdAt;
    private String updatedAt;
    private Client client;
    private Contracted contracted;
    private String local;
    private String username;

    public CraDto(){}
    public void setId(long id){
        this.id = id;
    }

    public String getId(){
        return Long.toString(this.id );
    }
    public void setType(String type){
        this.type = type;
    }
}
