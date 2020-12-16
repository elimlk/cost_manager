import Model.CostItem;
import Model.CostManagerException;
import Model.Currency;
import Model.DerbyDBModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Driver {
    public static void main(String[] args) throws CostManagerException, SQLException {
        DerbyDBModel db = new DerbyDBModel();
        //db.initDB();
        db.addCostItem(new CostItem(20,"food","test", Currency.ILS.toString(),new Date()));

    }
}

