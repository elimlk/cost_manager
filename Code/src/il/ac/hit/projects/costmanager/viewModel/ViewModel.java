package il.ac.hit.projects.costmanager.viewModel;

import il.ac.hit.projects.costmanager.model.CostItem;
import il.ac.hit.projects.costmanager.model.IModel;
import il.ac.hit.projects.costmanager.view.IView;

import java.util.List;

public class ViewModel implements IViewModel {

    private IModel model;
    private IView view;
    //private ExecutorService pool;

    public ViewModel(){

    }

    @Override
    public void setView(IView view) { this.view = view; }

    @Override
    public void setModel(IModel model) { this.model = model;}

    @Override
    public void addCostItem(CostItem item) {

    }

    @Override
    public List<String> getCategoriesKeys() {
        return model.getCategories();
    }
}
