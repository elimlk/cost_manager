package Model;

import java.sql.SQLException;
import java.util.Date;

public interface IModel {
    public void addCostItem(CostItem item) throws CostManagerException, SQLException;
    public Report showReport(String sDate,String eDate) throws CostManagerException ;
    public void addCategory (String category) ;
}
