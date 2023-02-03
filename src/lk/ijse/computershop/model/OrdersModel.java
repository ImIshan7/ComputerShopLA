package lk.ijse.computershop.model;
import lk.ijse.computershop.db.DBConnection;
import lk.ijse.computershop.to.Orders;
import lk.ijse.computershop.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersModel {

    public static String generateNextOID() throws SQLException, ClassNotFoundException {
        String sql = "SELECT OrderID FROM Orders ORDER BY OrderID DESC LIMIT 1";
        ResultSet result = CrudUtil.execute(sql);

        if (result.next()) {
            return generateNextOID(result.getString(1));
        }
        return generateNextOID(result.getString(null));
    }

    private static String generateNextOID(String currentOID) {
        if (currentOID != null) {
            String[] split = currentOID.split("O00");
            int oID = Integer.parseInt(split[1]);
            oID += 1;
            return "O00" + oID;
        }
        return "O001";
    }

    public static boolean addOrder(Orders order) throws SQLException, ClassNotFoundException {
        PreparedStatement statement= DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO orders VALUES(?,?,?,?) ");
        statement.setObject(1,order.getOrderID());
        statement.setObject(2,order.getCusID());
        statement.setObject(3,order.getDescription());
        statement.setObject(4,String.valueOf(order.getDate()));

        boolean isOrderAdded =statement.executeUpdate()>0;
        return isOrderAdded;
    }

}
