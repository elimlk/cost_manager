package ViewModel;

import Model.CostItem;
import Model.IModel;
import View.IView;

import java.util.List;

public interface IViewModel {
    public void setView(IView view);
    public void setModel(IModel model);
    public void addCostItem(CostItem item);
    List<String> getCategoriesKeys();
}
