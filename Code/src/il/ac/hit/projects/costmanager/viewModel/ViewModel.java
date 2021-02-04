package il.ac.hit.projects.costmanager.viewModel;

import il.ac.hit.projects.costmanager.model.*;
import il.ac.hit.projects.costmanager.view.IView;

import java.sql.SQLException;
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
    public void addCostItem(CostItem item) throws CostManagerException {
        model.addCostItem(item);

    }

    @Override
    public List<String> getCategoriesKeys() {
        return model.getCategories();
    }

    @Override
    public void addNewCat(String newCatName) throws CostManagerException{
            model.addCategory(newCatName);
    }

    @Override
    public Report getReport(String startDate, String endDate) throws CostManagerException {
        return model.getReport(startDate,endDate);

    }
}
