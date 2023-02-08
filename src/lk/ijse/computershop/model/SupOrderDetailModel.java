package lk.ijse.computershop.model;
import lk.ijse.computershop.db.DBConnection;
import lk.ijse.computershop.entity.SupOrderDetail;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupOrderDetailModel {

    private static boolean save(SupOrderDetail SupcartDetail) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO SupplierDetail VALUES(?,?,?,?,?)");

        statement.setObject(2, SupcartDetail.getPrdID());
        statement.setObject(1, SupcartDetail.getSupOrderID());
        statement.setObject(3, SupcartDetail.getQTY());
        statement.setObject(4, SupcartDetail.getDescripion());
        statement.setObject(5, SupcartDetail.getUnitPrice());

        return statement.executeUpdate() > 0;
    }

    public static boolean saveOrderDetail(ArrayList<SupOrderDetail> supOrderDetails) throws SQLException, ClassNotFoundException {
        for (SupOrderDetail supOrderDetail : supOrderDetails) {
            if (!save(supOrderDetail)) {
                return false;
            }

            }
        return true;
        }


    }