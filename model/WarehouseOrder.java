package model;

import java.time.LocalDate;

public class WarehouseOrder {

    private int id;
    private LocalDate date;
    private String status;

    public WarehouseOrder(int id, LocalDate date, String status)
    {
        this.id = id;
        this.date = date;
        this.status = status;
    }

    public int getId(){
        return id;
    }

    public LocalDate getDate(){
        return date;
    }

    public String getStatus(){
        return status;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

