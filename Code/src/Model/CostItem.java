package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CostItem {

    private double sum;
    private String details;
    private String category;
    private String currency;
    private int day;
    private int month;
    private  int year;

    //TODO Validation Tests
    public CostItem(double sum, String details, String category, String currency, String date) throws CostManagerException {
        setSum(sum);
        setDetails(details);
        setCategory(category);
        setCurrency(currency);
        if (isDateValid(date))
          setItemDate(date);
        else
            throw (new CostManagerException("Invalid Date"));

    }

    public static boolean isDateValid(String text) {
        if (text == null || !text.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$"))
            return false;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {
            df.parse(text);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    private void setItemDate(String date) {
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
    public String getDate(){
        return (year+"-"+month+"-"+day).toString();
    }
}
