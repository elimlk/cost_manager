package Model;

import java.util.Date;

public class CostItem {

    private double sum;
    private String details;
    private String category;
    private String currency;
    private int day;
    private int month;
    private  int year;

    //TODO Validation Tests
    public CostItem(double sum, String details, String category, String currency, int day,int month,int year) {
        setSum(sum);
        setDetails(details);
        setCategory(category);
        setCurrency(currency);
        setDay(day);
        setMonth(month);
        setYear(year);
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public void setDay(int day) { this.day = day; }
    public void setMonth(int month) { this.month = month; }
    public void setYear(int year) { this.year = year; }
    public double getSum() {
        return sum;
    }
    public String getDetails() {
        return details;
    }
    public String getCategory() {
        return category;
    }
    public String getCurrency() {
        return currency;
    }
    public int getDay() {return day;}
    public int getMonth() {return month;}
    public int getYear() {return year;}
}
