package il.ac.hit.projects.costmanager.model;

import java.sql.SQLException;
import java.util.List;

public interface IModel {
    public void addCostItem(CostItem item) throws CostManagerException;
    public Report getReport(String sDate, String eDate) throws CostManagerException ;
    public void addCategory (String category) throws CostManagerException;
    public void initDB();
    public List<String> getCategories ();
}
