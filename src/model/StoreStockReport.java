package model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class StoreStockReport {

    private int id;
    private LocalDate date;
    private String note;
    private Store store;
    private List<StoreStockReportItem> items;

    public StoreStockReport(int id, LocalDate date, String note, Store store, List<StoreStockReportItem> items) {
        this.id = id;
        this.date = date;
        this.note = note;
        this.store = store;
        this.items = items;
    }

    public StoreStockReport(LocalDate date, String note, Store store, List<StoreStockReportItem> items) {
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

    public void addItem(StoreStockReportItem item) {
        items.add(item);
    }

    public List<StoreStockReportItem> getItems() {
        return items;
    }

    public void setItems(List<StoreStockReportItem> items) {
        this.items = items;
    }
}

