package il.ac.hit.projects.costmanager.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CostItem {

    private double sum;
    private String details;
    private String category;
    private String currency;
    private String date;

    public CostItem(double sum, String details, String category, String currency, String date) throws CostManagerException {
        setSum(sum);
        setDetails(details);
        setCategory(category);
        setCurrency(currency);
        if (isDateValid(date))
          setItemDate(date);
        else
            throw (new CostManagerException("Invalid Date - Date must be YYYY-MM-DD"));

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
        this.date = date;
    }

    public void setSum(double sum) throws CostManagerException {
        if (sum > 0)
            this.sum = sum;
        else
            throw (new CostManagerException("Sum must be positive"));
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setCategory(String category) throws CostManagerException {
        DerbyDBModel dbModel = new DerbyDBModel();
        List categories = dbModel.getCategories();
        String output = category.substring(0, 1).toUpperCase() + category.substring(1).toLowerCase();
        if (categories.contains(output))
            this.category = output;
        else
            throw (new CostManagerException("Category does not exist"));
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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
    public String getDate(){
        return date;
    }
}
