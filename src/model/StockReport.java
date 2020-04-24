package model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class StockReport {

    private int id;
    private LocalDate date;
    private String note;
    private Store store;
    private List<StockReportItem> items;

    public StockReport(int id, LocalDate date, String note, Store store, List<StockReportItem> items) {
        this.id = id;
        this.date = date;
        this.note = note;
        this.store = store;
        this.items = items;
    }

    public StockReport(LocalDate date, String note, Store store, List<StockReportItem> items) {
        this.date = date;
        this.note = note;
        this.store = store;
        this.items = items;
    }

    public int getId(){
        return id;
    }

    public Date getDate(){
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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void addItem(StockReportItem item) {
        items.add(item);
    }

    public List<StockReportItem> getItems() {
        return items;
    }

    public void setItems(List<StockReportItem> items) {
        this.items = items;
    }
}

