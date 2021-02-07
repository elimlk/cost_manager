/*
Submitted by:
Itamar Yacoby 203023338
Eliran Malka 302830617
Idan Shturm 208535674
 */

package il.ac.hit.projects.costmanager.model;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DerbyDBModel implements IModel {

    String databaseURL = "jdbc:derby:costsManagerDB1;create=true";
    String tableName = "costTableTest1";


    public void initDB() {
        //Initializing Database and creating the Table if doesn't exists
        try (Connection conn = DriverManager.getConnection(databaseURL)) { //Try with resources meaning connection closes automatically
            Statement statement = conn.createStatement();
            if (!doesTableExists(tableName, conn)) {
                String sql = "CREATE TABLE "+ tableName+" (cost_id int primary key, cost_details varchar(128)," +
                        " cat varchar(128), currency varchar(128),date DATE,my_sum double)";
                //Format table :
                // ID | DETAILS | CATEGORY | CURRENCY | DATE | SUM
                statement.execute(sql);
                System.out.println("Created table"+tableName);

                String[] categories = {"Food", "Bills", "Cloths", "Cars", "Electronics"}; //Default Categories
                int index;
                //Adding dummy rows for categories with negative indexs for easier identification
                for (index=1;index<categories.length+1;index++ ){

                    String cat = categories[index-1];
                    sql = "INSERT INTO "+tableName+" VALUES ("+-1*index+", NULL,'"+cat+"', NULL, NULL, NULL)";
                    statement.execute(sql);
                    System.out.println("insert cat row");
                }
            }
            statement.close();
        } catch (SQLException ex) {
                ex.printStackTrace();
        }
    }

    private static boolean doesTableExists(String tableName, Connection conn) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet result = meta.getTables(null, null, tableName.toUpperCase(), null);
        return result.next();
    }

    public int lastIdRecord(){
        //Returning the id of the most recent cost item in table
        try (Connection conn = DriverManager.getConnection(databaseURL)) {
            Statement statement = conn.createStatement();
            String sql = "SELECT MAX(cost_id) as cost_id FROM "+tableName;
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                return (result.getInt("cost_id"));
            }
            statement.close();
            result.close();
        } catch (SQLException ex) {
                ex.printStackTrace();
        }
        return -1; //Default return meaning no cost items in table
    }

    public int lastCategoryID(){
        //Returning the most recent added Category
        try (Connection conn = DriverManager.getConnection(databaseURL)) {
            Statement statement = conn.createStatement();
            String sql = "SELECT MIN(cost_id) as cost_id FROM "+tableName;
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                return (result.getInt("cost_id"));
            }

            statement.close();
            result.close();

        } catch (SQLException ex) {
                ex.printStackTrace();
        }
        return -1; //Default return meaning no categories in table
    }

    @Override
    public void addCostItem (CostItem item) {
        try (Connection conn = DriverManager.getConnection(databaseURL)) {
            // ID | DETAILS | CATEGORY | CURRENCY | DATE | SUM
            Statement statement = conn.createStatement();
            int recordId = lastIdRecord()+1;
            String sql = "INSERT INTO "+tableName+" VALUES ("+recordId+",'"+item.getDetails()+"','"+item.getCategory()+"','"+
                                item.getCurrency()+"','"+item.getDate()+"',"+item.getSum()+")";
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    @Override
    public Report getReport(String sDate, String eDate) throws CostManagerException {
        //Returning a Report object with cost items from start date to end date

        Report report = new Report(getCategories());
        CostItem currentCostItem;

        if (!(CostItem.isDateValid(sDate) && CostItem.isDateValid(eDate)))
            throw new CostManagerException("Date must be in format 'YYYY-MM--DD'");

        try (Connection conn = DriverManager.getConnection(databaseURL)) {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM " + tableName +
                    " WHERE date BETWEEN '" + sDate + "' AND '" + eDate + "'";

            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                currentCostItem = new CostItem(
                        result.getString("my_sum"),
                        result.getString("cost_details"),
                        result.getString("cat"),
                        result.getString("currency"),
                        result.getDate("date").toString());
                report.addItem(currentCostItem);
            }
            statement.close();
            result.close();

        } catch (SQLException e) {
                e.printStackTrace();

        } finally {
            return report;
        }
    }

    @Override
    public void addCategory(String newCategory) throws CostManagerException {

        String input = newCategory.substring(0, 1).toUpperCase() + newCategory.substring(1).toLowerCase();//Unifying capitalization
        int nextID = lastCategoryID()-1; //Categories ID are negative numbers

        try (Connection conn = DriverManager.getConnection(databaseURL)) {
            if (isCategoryExists(input))
                throw (new CostManagerException("Category already exists"));
            // ID | DETAILS | CATEGORY | CURRENCY | DATE | SUM
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO "+tableName+" VALUES ("+nextID+", NULL,'"+input+"', NULL, NULL, NULL)";
            statement.execute(sql);
            statement.close();

        } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    private boolean isCategoryExists(String newCategory) {

        try (Connection conn = DriverManager.getConnection(databaseURL)) {
            Statement statement = conn.createStatement();
            String sql = "SELECT cat FROM "+tableName;
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                if (result.getString("cat").equals(newCategory)) {
                    statement.close();
                    result.close();
                    return true;
                }
            }
            statement.close();
            result.close();

        } catch (SQLException ex) {
                ex.printStackTrace();
        }

        return false;
    }

    public List<String> getCategories (){
        //Returning a list consisting all categories in table
        List <String> categories = new ArrayList<String>();
        try (Connection conn = DriverManager.getConnection(databaseURL)) {
            Statement statement = conn.createStatement();
            String sql = "SELECT DISTINCT cat FROM " + tableName;
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                categories.add(result.getString("cat"));
            }
            statement.close();
            result.close();

        } catch (SQLException e) {
                e.printStackTrace();
        }

        finally {
            return categories;
        }
    }

  /*
    public void printTable(){
        try (Connection conn = DriverManager.getConnection(databaseURL)) {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM "+tableName;
            ResultSet result = statement.executeQuery(sql);
            // ID | DETAILS | CATEGORY | CURRENCY | DATE | SUM
            while (result.next()) {
                System.out.print(Integer.parseInt(result.getString("cost_id"))+",");
                System.out.print(result.getString("cost_details")+",");
                System.out.print(result.getString("cat")+",");
                System.out.print(result.getString("currency")+",");
                System.out.print(result.getDate("date")+",");
                System.out.print(result.getDouble("my_sum"));
                System.out.println("");
            }
            DriverManager.getConnection("jdbc:derby:costsManagerDB1;shutdown=true");
        } catch (SQLException e) {
            if (e.getSQLState().equals("08006")) {
                System.out.println("Derby shutdown normally");
            } else {
                e.printStackTrace();
            }
        }
    }
    */
}

