package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

    private String username;
    private String password;
    private List<String> uniqueCategories;
    private List<Cost> costs;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.uniqueCategories = new ArrayList<String>();
        this.costs = new ArrayList<Cost>();
    }

    public boolean addCost(double sum, String details, String category, String currency, Date date){
        try {
            costs.add(new Cost(sum,details,category,currency,date));
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
        if(uniqueCategories.contains(newCategory)){
            return false;
        }else{
            try {
                this.uniqueCategories.add(newCategory);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

}

