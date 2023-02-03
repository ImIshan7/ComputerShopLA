package lk.ijse.computershop.model;

import lk.ijse.computershop.db.DBConnection;
import lk.ijse.computershop.to.OrderDetail;
import lk.ijse.computershop.util.CrudUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailModel {



    public static boolean saveOrderDetail(ArrayList<OrderDetail> orderDetails) throws SQLException, ClassNotFoundException {
        for (OrderDetail orderDetail :orderDetails ) {
            if (!save(orderDetail)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(OrderDetail cartDetail) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO OrderDetail VALUES(?,?,?,?)");

        statement.setObject(1, cartDetail.getOrderID());
        statement.setObject(2, cartDetail.getPrdID());
        statement.setObject(3, cartDetail.getQTY());
        statement.setObject(4, cartDetail.getPrice());


        return statement.executeUpdate() > 0;
       }


    public static int OrdersCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) from OrderDetail";
        ResultSet result = CrudUtil.execute(sql);

        if (result.next())
            return result.getInt(1);
        return 0;
    }


}
