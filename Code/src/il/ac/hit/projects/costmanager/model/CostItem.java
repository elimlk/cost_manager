/*
Submitted by:
Itamar Yacoby 203023338
Eliran Malka 302830617
Idan Shturm 208535674
 */

package il.ac.hit.projects.costmanager.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CostItem {
    //cost item class represents cost expense in data base
    private double sum;
    private String details;
    private String category;
    private String currency;
    private String date;

    public CostItem(String sum, String details, String category, String currency, String date) throws CostManagerException {
        setSum(sum);
        setDetails(details);
        setCategory(category);
        setCurrency(currency);
        setItemDate(date);
    }

    public static boolean isDateValid(String date) {

        if (date == null || !date.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")) //Check if date is yyyy-MM-dd
            return false;
        return true;
    }

    private void setItemDate(String date) throws CostManagerException {
        if (isDateValid(date))
            this.date = date;
        else
            throw (new CostManagerException("Invalid Date - Date must be YYYY-MM-DD"));

    }

    public void setSum(String sum) throws CostManagerException {

        try {
            Double parseSum = Double.parseDouble(sum);
            if (parseSum > 0) {
                this.sum = parseSum;
            }
            else throw (new CostManagerException("Sum must be positive"));
        }
         catch (Exception e) {
            throw (new CostManagerException("Sum must be a number"));
        }
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setCategory(String category) throws CostManagerException {
        DerbyDBModel dbModel = new DerbyDBModel();
        List categories = dbModel.getCategories();
        String output = category.substring(0, 1).toUpperCase() + category.substring(1).toLowerCase(); // handle with typo and unified pattern capital letter on start and small after
        if (categories.contains(output))
            this.category = output;
        else
            throw (new CostManagerException("Category does not exist"));
    }

    public void setCurrency(String currency) throws CostManagerException {
        String upperCurrency = currency.toUpperCase();
        for (Currency c : Currency.values()) {  //check is currency exist in ENUM Currency.
            if (c.name().equals(upperCurrency)) {
                this.currency = upperCurrency;
                return;
            }
        }
        throw (new CostManagerException("Invalid Currency"));

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
