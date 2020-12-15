package Model;
import org.apache.derby.client.am.SqlException;

import java.sql.*;

public class DerbyDBModel implements IModel {
    public static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    public static String protocol = "jdbc:derby://localhost:1527/costM1;create=true";

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
    }

    @Override
    public void addCostItem (CostItem item) throws CostManagerException {
    }
    @Override
    public void deleteCostItem (CostItem item) throws CostManagerException {
    }
}
