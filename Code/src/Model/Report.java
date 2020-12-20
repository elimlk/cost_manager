package Model;

import java.util.ArrayList;
import java.util.List;

public class Report {

    private List<CostItem> listOfItems;

    public Report() {
        this.listOfItems = new ArrayList<CostItem>();
    }

    public void addItem(CostItem costItem){
        listOfItems.add(costItem);
    }

    public double calcSummery() {
        double sum = 0;
        for(int i=0 ;i < listOfItems.size();i++){
            sum += listOfItems.get(i).getSum();
        }
        return sum;
    }

    public void calcPercentCategory(){

    }


}
