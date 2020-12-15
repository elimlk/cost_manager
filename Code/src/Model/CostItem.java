package Model;

import java.util.Date;

public class CostItem {

    private double sum;
    private String details;
    private String category;
    private String currency;
    private Date date;

    //TODO Validation Tests
    public CostItem(double sum, String details, String category, String currency, Date date) {
        setSum(sum);
        setDetails(details);
        setCategory(category);
        setCurrency(currency);
        setDate(date);
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
    public void setDate(Date date) {
        this.date = date;
    }
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
    public Date getDate() {
        return date;
    }


}
