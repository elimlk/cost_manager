package il.ac.hit.projects.costmanager.viewModel;

import il.ac.hit.projects.costmanager.model.CostItem;
import il.ac.hit.projects.costmanager.model.IModel;
import il.ac.hit.projects.costmanager.view.IView;

import java.util.List;

public interface IViewModel {
    public void setView(IView view);
    public void setModel(IModel model);
    public void addCostItem(CostItem item);
    List<String> getCategoriesKeys();
}
