package Model;

import java.util.Date;

public interface IModel {
    public void addCostItem(CostItem item) throws CostManagerException;
    public void deleteCostItem(CostItem item) throws CostManagerException;
    public void showReport(Date start,Date end) throws CostManagerException ;
    public void addCategory(String newCategory);
}
