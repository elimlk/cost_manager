import Model.CostItem;
import Model.CostManagerException;
import Model.Currency;
import Model.DerbyDBModel;

public class Driver {
    public static void main(String[] args) {
        // ID | DETAILS | CATEGORY | CURRENCY | DATE | SUM
        DerbyDBModel db = new DerbyDBModel();
        db.initDB();
       try {
            db.addCostItem(new CostItem(20,"test_throw","CaRs", Currency.ILS.toString(),"2000-02-26"));
            db.addCategory("baby");
        } catch (CostManagerException e) {
            System.out.println(e.getMessage());
        } // add items for tests.
        db.printTable();
        try {
            db.showReport("1991-01-01","1991-02-23");
            System.out.println("tt");
        } catch (CostManagerException e) {
            e.printStackTrace();
        }
    }
}

