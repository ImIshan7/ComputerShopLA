package lk.ijse.computershop.model;

import lk.ijse.computershop.db.DBConnection;
import lk.ijse.computershop.to.OrderDetail;
import lk.ijse.computershop.to.SupOrderDetail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupProductModel {

    public static boolean updateQty(ArrayList<SupOrderDetail> suporderDetails) throws SQLException, ClassNotFoundException {
        for (SupOrderDetail cartDetail : suporderDetails) {
            if (!updateQty(cartDetail)){
                return false;
            }
        }
        return true;

    }

    private static boolean updateQty(SupOrderDetail supOrderDetail) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE Product SET QTY = QTY +? WHERE PrdID =?");
        stm.setObject(1, supOrderDetail.getQTY());
        stm.setObject(2, supOrderDetail.getPrdID());

        return stm.executeUpdate() > 0;
    }
}
