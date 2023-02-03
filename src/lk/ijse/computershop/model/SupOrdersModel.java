package lk.ijse.computershop.model;
import lk.ijse.computershop.db.DBConnection;
import lk.ijse.computershop.to.SupOrders;
import lk.ijse.computershop.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupOrdersModel {
    public static boolean save( SupOrders suporders) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO SupOrders VALUES (?, ?, ?,)";
        return CrudUtil. execute(sql,suporders.getSupOrderID(), suporders.getSupID(),suporders.getDate());
    }
//generateNextOID
public static String generateNextOID(){
    try {
        ResultSet resultSet= CrudUtil.execute("SELECT SupOrderID FROM SupOrders ORDER BY SupOrderID DESC LIMIT 1");

        if(resultSet.next())return generateNextOID(resultSet.getString(1));

    } catch (Exception e) {
        e.printStackTrace();
    }
    return generateNextOID(null);

}
    public static  String generateNextOID(String currentItemNo){
        if(currentItemNo !=null){
            String[] split = currentItemNo.split("SOP");
            int id = Integer.parseInt(split[1]);
            id += 1;
            if (id >=10){
                return "SOP0"+id;
            }else {
                return "SOP00"+id;
            }
        }
        return "SOP001";
    }

    public static boolean addOrder(SupOrders supOrders) throws SQLException, ClassNotFoundException {
        PreparedStatement statement= DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO SupOrders VALUES(?,?,?) ");
        statement.setObject(1,supOrders.getSupOrderID());
        statement.setObject(2,supOrders.getSupID());
        statement.setObject(3,String.valueOf(supOrders.getDate()));

        boolean isOrderAdded =statement.executeUpdate()>0;
        return isOrderAdded;
    }
}
