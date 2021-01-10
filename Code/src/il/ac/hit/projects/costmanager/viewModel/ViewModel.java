package il.ac.hit.projects.costmanager.viewModel;

import il.ac.hit.projects.costmanager.model.CostItem;
import il.ac.hit.projects.costmanager.model.CostManagerException;
import il.ac.hit.projects.costmanager.model.DerbyDBModel;
import il.ac.hit.projects.costmanager.model.IModel;
import il.ac.hit.projects.costmanager.view.IView;
import il.ac.hit.projects.costmanager.view.View;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewModel implements IViewModel {

    private IModel model;
    private IView view;
    private ExecutorService pool;

    public ViewModel(){
        pool = Executors.newFixedThreadPool(10);
    }

    @Override
    public void setView(IView view) {
        this.view = view;
    }

    @Override
    public void setModel(IModel model) {
        this.model = model;
    }

    @Override
    public void addCostItem(CostItem item) {

    }

    @Override
    public List<String> getCategoriesKeys() {
        return model.getCategories();
    }

    @Override
    public void addNewCat(String newCatName) {
        try {
            model.addCategory(newCatName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
