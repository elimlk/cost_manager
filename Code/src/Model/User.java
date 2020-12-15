/*
package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

    private String username;
    private String password;
    private Category categories;
    private List<CostItem> costItems;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.categories = new Category();
        this.costItems = new ArrayList<CostItem>();
    }

    public boolean addCost(double sum, String details, String category, String currency, Date date){
        try {
            costItems.add(new CostItem(sum,details,category,currency,date));
        } catch (Exception e) {
            //TODO ask life about that
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addCategory(String newCategory){
        //TODO ask life about exceptions message
        if (newCategory.isBlank()){
            return false;
        }
        if(categories.contains(newCategory)){
            return false;
        }else{
            try {
                this.categories.add(newCategory);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    
}

*/
