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

