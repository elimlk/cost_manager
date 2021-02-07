/*
Submitted by:
Itamar Yacoby 203023338
Eliran Malka 302830617
Idan Shturm 208535674
 */

package il.ac.hit.projects.costmanager.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



//Report object represents a list of cost items between two dates to make calculations easier on them (cost sum for each category)
public class Report {
    private List<CostItem> listOfItems;
    private List<String> listOfCategories;

    public Report(List<String> listOfCategories) {
        this.listOfItems = new ArrayList<CostItem>();
        this.listOfCategories = listOfCategories;
    }

    public void addItem(CostItem costItem){
        listOfItems.add(costItem);
    }

    public HashMap<String,Double> calcReport() {

        HashMap<String, Double> categoriesSum = new HashMap<String, Double>();
        //initialize report with all categories
        for (String category :listOfCategories)
        {
            categoriesSum.put(category,0.0);
        }
        // summery of items on each category
        for(CostItem item : listOfItems){
            Double old = categoriesSum.get(item.getCategory());
            Double newSum = convertCurenncy(item);
            categoriesSum.replace(item.getCategory(),old+newSum);
        }

        return categoriesSum;
    }

    private Double convertCurenncy(CostItem item) {
        // convert sum with currency rate to ILS
        String currency = item.getCurrency();
        Double convertedSum = 0.0;
        switch (currency){
            case("ILS"):{
                convertedSum  = item.getSum();
                break;
            }
            case("USD"):
                convertedSum = item.getSum() * 3.3;
                break;
            case("EUR"):
                convertedSum = item.getSum() * 3.9;
                break;
            case("GBP"):
                convertedSum = item.getSum() * 4.4;
                break;
        }
        return convertedSum;
    }
}
