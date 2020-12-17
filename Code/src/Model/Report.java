package Model;

import java.util.ArrayList;
import java.util.List;

public class Report {

    private List<CostItem> listOfItems;


    public void addItem(CostItem costItem){
        listOfItems = new ArrayList<CostItem>();
        listOfItems.add(costItem);
    }


}
