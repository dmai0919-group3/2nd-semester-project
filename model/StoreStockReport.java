package model;

import java.time.LocalDate;

public class StoreStockReport {

    private int id;
    private LocalDate date;
    private String note;

    public StoreStockReport(int id, LocalDate date, String note) {
        this.id = id;
        this.date = date;
        this.note = note;
    }


    public int getId(){
        return id;
    }

    public LocalDate getDate(){
        return date;
    }

    public String getNote(){
        return note;
    }


    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setNote(String note) {
        this.note = note;
    }

}

