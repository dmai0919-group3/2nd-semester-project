package model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class StockReport {

    private int id;
    private LocalDate date;
    private String note;
    private List<StockReportItem> items;

    public StockReport(int id, LocalDate date, String note) {
        this.id = id;
        this.date = date;
        this.note = note;
        items = new LinkedList<>();
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

    public void addItem(StockReportItem item) {
        items.add(item);
    }
    public List<StockReportItem> getItems() {
        return items;
    }

}

