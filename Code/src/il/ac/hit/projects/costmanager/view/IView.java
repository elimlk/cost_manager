package il.ac.hit.projects.costmanager.view;

import il.ac.hit.projects.costmanager.model.CostItem;
import il.ac.hit.projects.costmanager.viewModel.IViewModel;
//import java.util.Map;

public interface IView  {
  //  public void displayPieChart(Map map);
    public void setViewModel(IViewModel viewModel);
    public void showMessage(String msg);
    public void showItems(CostItem[] item);

}
