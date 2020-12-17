package Model;

import java.sql.SQLException;
import java.util.Date;

public interface IModel {
    public void addCostItem(CostItem item) throws CostManagerException, SQLException;
    public void deleteCostItem(CostItem item) throws CostManagerException;
    public Report showReport(int s_day,int s_month,int s_year,int e_day,int e_month,int e_year) throws CostManagerException ;
    public void addCategory(String newCategory);
}
