import Model.CostItem;
import Model.CostManagerException;
import Model.Currency;
import Model.DerbyDBModel;

public class Driver {
    public static void main(String[] args) {
        DerbyDBModel db = new DerbyDBModel();
        db.initDB();
 /*       try {
            db.addCostItem(new CostItem(200.3,"test1","test", Currency.ILS.toString(),1,1,1991));
        } catch (CostManagerException e) {
            e.printStackTrace();
        }*/ // add items for tests.
        db.printTable();

    }
}

