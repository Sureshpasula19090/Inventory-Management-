package com.javaProblem.inventory;

public class Record {
    public Record(String item, Float costPrice, Float sellingPrice) {
        this.item = item;
        this.setBoughtAt(costPrice);
        this.setSoldAt(sellingPrice);
    }

    private String item;
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    private Float boughtAt;
    private Float soldAt;
    private int availableQty = 0;
    private int value;

    @Override
    public boolean equals(Object o) {
        if (o != null && o.getClass() == Record.class) {
            Record r = (Record) o;
            if (r.item.equals(this.item))
                return true;
            else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Float getBoughtAt() {
        return boughtAt;
    }

    public void setBoughtAt(Float boughtAt) {
        this.boughtAt = boughtAt;
    }

    public Float getSoldAt() {
        return soldAt;
    }

    public void setSoldAt(Float sellingPrice) {
        this.soldAt = sellingPrice;
    }

    public int getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(int availableQty) {
        this.availableQty = availableQty;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
