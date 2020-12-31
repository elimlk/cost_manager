package View;

import Model.CostItem;
import ViewModel.IViewModel;
//import java.util.Map;

public interface IView  {
  //  public void displayPieChart(Map map);
    public void setViewModel(IViewModel viewModel);
    public void showMessage(String msg);
    public void showItems(CostItem[] item);

}
