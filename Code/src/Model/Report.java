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


}
