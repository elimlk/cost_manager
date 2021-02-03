package il.ac.hit.projects.costmanager;

import il.ac.hit.projects.costmanager.model.IModel;
import il.ac.hit.projects.costmanager.view.IView;
import il.ac.hit.projects.costmanager.viewModel.IViewModel;

import il.ac.hit.projects.costmanager.model.DerbyDBModel;
import il.ac.hit.projects.costmanager.view.View;
import il.ac.hit.projects.costmanager.viewModel.ViewModel;

public class  Driver {

    public static void main(String[] args) {
        // ID | DETAILS | CATEGORY | CURRENCY | DATE | SUM
/*        DerbyDBModel db = new DerbyDBModel();
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
        }*/

        //creating the application components
        IModel model = new DerbyDBModel();
        model.initDB();
        IViewModel vm = new ViewModel();
        IView view = new View();

        //connecting the components with each other
        view.setViewModel(vm);
        vm.setModel(model);
        vm.setView(view);

    }
}

