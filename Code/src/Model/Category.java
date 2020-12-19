package Model;
import java.util.ArrayList;
import java.util.List;

public class Category {

    private List<String> categories;

    public Category (){
        categories = new ArrayList<String>();
    }

    public void addCategory(String newCategory){
        categories.add(newCategory);
    }

}
