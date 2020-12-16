package Model;
import org.apache.derby.client.am.SqlException;
import java.sql.*;
import java.util.Date;

public class DerbyDBModel implements IModel {
  //  public static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
  //  public static String protocol = "jdbc:derby://localhost:1527/costM1;create=true";
/*
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = null;
            //Instantiating the dirver class will indirectly register
            //this driver as an available driver for DriverManager
            Class.forName(driver);
            //Getting a connection by calling getConnection
            connection = DriverManager.getConnection(protocol);
            statement = connection.createStatement();
            statement.execute("create table inventory(nameCost char,id int, fee double)");
            statement.execute("insert into inventory values ('fuel',100212,2.5)");
            statement.execute("insert into inventory values ('food',100213,1.2)");
            statement.execute("insert into inventory values ('itamar',100214,1.2)");
            rs = statement.executeQuery(
                    "SELECT id,fee FROM inventory ORDER BY id");
            while(rs.next())
            {
                System.out.println("name"+rs.getString("nameCost")+"id="+rs.getInt("id")
                        +" fee="+rs.getDouble("fee"));
            }
            statement.execute("DROP TABLE inventory");
        }
        catch(SQLException | ClassNotFoundException e) { e.printStackTrace(); }
        finally
        {
            if(statement!=null) try{statement.close();}catch(Exception e){}
            if(connection!=null) try{connection.close();}catch(Exception e){}
            if(rs!=null) try{rs.close();}catch(Exception e){}
        }
    }*/

    String databaseURL = "jdbc:derby:costsManagerDB1;create=true";
    String tableName = "costs";

    public void initDB() {

        try (Connection conn = DriverManager.getConnection(databaseURL)) {
            Statement statement = conn.createStatement();
            if (!doesTableExists(tableName, conn)) {
                String sql = "CREATE TABLE "+ tableName+" (cost_id int primary key, cost_details varchar(128)," +
                        " cat varchar(128), currency varchar(128), day int, month int, my_year int, my_sum double)";
                statement.execute(sql);
                System.out.println("Created table"+tableName);
                sql = "INSERT INTO "+tableName+" VALUES (12345, 'test cost row','test','ILS',1,1,1990,0)";
                statement.execute(sql);
                System.out.println("Inserted test row.");


            }
            String sql = "SELECT * FROM costs";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                System.out.println(result.getString("cost_id"));
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

    private int lastIdRecord(){
        try (Connection conn = DriverManager.getConnection(databaseURL)) {
            Statement statement = conn.createStatement();
            String sql = "SELECT cost_id FROM costs ORDER BY cost_id DESC LIMIT 1 ";
            ResultSet result = statement.executeQuery(sql);
            return Integer.parseInt(result.getString("cost_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    @Override
    public void addCostItem (CostItem item) throws CostManagerException {
        try (Connection conn = DriverManager.getConnection(databaseURL)) {
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO " + tableName + " VALUES ("+item.getDetails()+"00000, 'test cost row','test','ILS',1,1,1990,0)";
            statement.execute(sql);
            DriverManager.getConnection("jdbc:derby:costsManagerDB1;shutdown=true");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteCostItem (CostItem item) throws CostManagerException {
    }
    @Override
    public void showReport(Date start, Date end) throws CostManagerException {
    }
    @Override
    public void addCategory(String newCategory) {
    }
}
