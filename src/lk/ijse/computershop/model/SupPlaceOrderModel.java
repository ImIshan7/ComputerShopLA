package lk.ijse.computershop.model;

import lk.ijse.computershop.db.DBConnection;
import lk.ijse.computershop.to.SupOrders;

import java.sql.SQLException;

public class SupPlaceOrderModel {


    public static boolean SupPlaceOrder(SupOrders supOrders) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isOrderAdded = SupOrdersModel.addOrder(supOrders);
            if (isOrderAdded) {
                System.out.println("orderAdded");
                boolean isUpdated = SupProductModel.updateQty(supOrders.getSupOrderDetails());
                if (isUpdated) {
                    System.out.println("Updated");
                    boolean isOrderDetailAdded = SupOrderDetailModel.saveOrderDetail(supOrders.getSupOrderDetails());
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
