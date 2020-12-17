import Model.DerbyDBModel;

public class Driver {
    public static void main(String[] args) {
        DerbyDBModel db = new DerbyDBModel();
        db.initDB();
        System.out.println(db.lastIdRecord());

    }
}

