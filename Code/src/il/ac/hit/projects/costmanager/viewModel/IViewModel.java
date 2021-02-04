package il.ac.hit.projects.costmanager.viewModel;

import il.ac.hit.projects.costmanager.model.CostItem;
import il.ac.hit.projects.costmanager.model.CostManagerException;
import il.ac.hit.projects.costmanager.model.IModel;
import il.ac.hit.projects.costmanager.model.Report;
import il.ac.hit.projects.costmanager.view.IView;

import java.sql.SQLException;
import java.util.List;

public interface IViewModel {
    public void setView(IView view);
    public void setModel(IModel model);
    public void addCostItem(CostItem item) throws CostManagerException;
    List<String> getCategoriesKeys();

    void addNewCat(String newCatName) throws CostManagerException;

    Report getReport(String startDate, String endDate) throws CostManagerException;
}
