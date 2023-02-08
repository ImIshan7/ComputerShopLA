package lk.ijse.computershop.model;
import lk.ijse.computershop.db.DBConnection;
import lk.ijse.computershop.entity.Orders;
import java.sql.SQLException;


public class PlaceOrderModel {

    public static boolean placeOrder(Orders order) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isOrderAdded = OrdersModel.addOrder(order);
            if (isOrderAdded) {
                System.out.println("orderAdded");
                boolean isUpdated = ProductModel.updateQty(order.getOrderDetails());
                if (isUpdated) {
                    System.out.println("Updated");
                    boolean isOrderDetailAdded = OrderDetailModel.saveOrderDetail(order.getOrderDetails());
                    if (isOrderDetailAdded) {
                        System.out.println("DetailsAdded");
                        DBConnection.getInstance().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } finally {

            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }
}
