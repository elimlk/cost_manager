/*
Submitted by:
Itamar Yacoby 203023338
Eliran Malka 302830617
Idan Shturm 208535674
 */

package il.ac.hit.projects.costmanager.view;

import il.ac.hit.projects.costmanager.model.CostItem;
import il.ac.hit.projects.costmanager.viewModel.IViewModel;
//import java.util.Map;

public interface IView  {
  //  public void displayPieChart(Map map);
    public void setViewModel(IViewModel viewModel);
    public void showMessage(String msg);
}
