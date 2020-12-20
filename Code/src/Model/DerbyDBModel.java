package Model;
import java.sql.*;

public class DerbyDBModel implements IModel {

    String databaseURL = "jdbc:derby:costsManagerDB1;create=true";
    String tableName = "costE";

    public void initDB() {

        try (Connection conn = DriverManager.getConnection(databaseURL)) {
            Statement statement = conn.createStatement();
            if (!doesTableExists(tableName, conn)) {
                String sql = "CREATE TABLE "+ tableName+" (cost_id int primary key, cost_details varchar(128)," +
                        " cat varchar(128), currency varchar(128),date DATE,my_sum double)";
                //Format table :
                // ID | DETAILS | CATEGORY | CURRENCY | DATE | SUM
                statement.execute(sql);
                System.out.println("Created table"+tableName);
                sql = "INSERT INTO "+tableName+" VALUES (1111, 'test cost row','test','ILS','1990-01-01',0)";
                statement.execute(sql);
                System.out.println("Inserted test row.");


            }
            DriverManager.getConnection("jdbc:derby:costsManagerDB1;shutdown=true");
        } catch (SQLException ex) {
            if (ex.getSQLState().equals("08006")) {
                System.out.println("Derby shutdown normally");
            } else {
                ex.printStackTrace();
            }
        }
    }
    private static boolean doesTableExists(String tableName, Connection conn)
            throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet result = meta.getTables(null, null, tableName.toUpperCase(), null);

        return result.next();
    }

    public int lastIdRecord(){
        try (Connection conn = DriverManager.getConnection(databaseURL)) {
            Statement statement = conn.createStatement();
            String sql = "SELECT MAX(cost_id) as cost_id FROM "+tableName;
//            String sql = "SELECT * FROM "+tableName;
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                return Integer.parseInt(result.getString("cost_id"));
            }
            DriverManager.getConnection("jdbc:derby:costsManagerDB1;shutdown=true");
        } catch (SQLException ex) {
            if (ex.getSQLState().equals("08006")) {
                System.out.println("Derby shutdown normally");
            } else {
                ex.printStackTrace();
            }
        }
        return -1;
    }

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
                System.out.print(Double.parseDouble(result.getString("my_sum"))+",");
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

    @Override
    public void addCostItem (CostItem item) throws CostManagerException {
        try (Connection conn = DriverManager.getConnection(databaseURL)) {
            // ID | DETAILS | CATEGORY | CURRENCY | DATE | SUM
            Statement statement = conn.createStatement();
            int Record_id = lastIdRecord()+1;
            String sql = "INSERT INTO "+tableName+" VALUES ("+Record_id+",'"+item.getDetails()+"','"+item.getCategory()+"','"+
                                item.getCurrency()+"','"+item.getDate()+"',"+item.getSum()+")";
            statement.execute(sql);
            DriverManager.getConnection("jdbc:derby:costsManagerDB1;shutdown=true");
        } catch (SQLException e) {
            if (e.getSQLState().equals("08006")) {
                System.out.println("Derby shutdown normally");
            } else {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void deleteCostItem (CostItem item) throws CostManagerException {

    }
    @Override
    public Report showReport(String sDate,String eDate) throws CostManagerException {
        Report report = new Report();
        CostItem costItem ;
        try (Connection conn = DriverManager.getConnection(databaseURL)) {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM "+tableName+
                            " WHERE date BETWEEN '"+sDate+"' AND '"+ eDate+"'";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                costItem = new CostItem(
                        result.getDouble("my_sum"),
                        result.getString("cost_details"),
                        result.getString("cat"),
                        result.getString("currency"),
                        result.getDate("date").toString());
                        report.addItem(costItem);
            }
            DriverManager.getConnection("jdbc:derby:costsManagerDB1;shutdown=true");
        } catch (SQLException e) {
            if (e.getSQLState().equals("08006")) {
                System.out.println("Derby shutdown normally");
            } else {
                e.printStackTrace();
            }
        }finally {
            return report;
        }

    }

}
