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
    public CostItem(double sum, String details, String category, String currency, String date) {
        setSum(sum);
        setDetails(details);
        setCategory(category);
        setCurrency(currency);
        parseDate(date);

    }

    private void parseDate(String date) {
        setYear(Integer.parseInt(date.substring(0,4)));
        setMonth(Integer.parseInt(date.substring(5,7)));
        setDay(Integer.parseInt(date.substring(8)));
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
    public void setDay(int day) {
        //TODO check february month;
        if (this.month % 2==0 )
            if(day <= 31 && day >= 1)
                this.day = day;
        else{
            if(day <= 30 && day >= 1)
                this.day = day;
            }
    }
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
    public String getDate(){
        return (year+"-"+month+"-"+day).toString();
    }
}
