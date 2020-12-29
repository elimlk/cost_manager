package ViewModel;

import Model.CostItem;
import Model.IModel;
import View.IView;

public interface IViewModel {
    public void setView(IView view);
    public void setModel(IModel model);
    public void addCostItem(CostItem item);


}
