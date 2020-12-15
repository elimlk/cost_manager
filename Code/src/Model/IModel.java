package Model;

public interface IModel {
    public void addCostItem(CostItem item) throws CostManagerException;
    public void deleteCostItem(CostItem item) throws CostManagerException;

}
