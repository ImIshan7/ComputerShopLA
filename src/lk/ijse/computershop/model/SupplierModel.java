package lk.ijse.computershop.model;


import lk.ijse.computershop.entity.Supplier;
import lk.ijse.computershop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierModel {

    public static boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO Supplier VALUES (?, ?, ?, ?, ?, ?)";
        return CrudUtil.execute(sql,supplier.getSupid(), supplier.getName(), supplier.getAddress(), supplier.getBrand(),supplier.getUnit_Price(),supplier.getQty());
    }

    public static Supplier search(String SupID) throws SQLException, ClassNotFoundException {

        String sql = "SELECT  * FROM Supplier WHERE SupID = ?";
        ResultSet result = CrudUtil.execute(sql, SupID);

        if(result.next()) {
            return new Supplier(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getDouble(5),
                    result.getInt(6)
            );
        }
        return null;
    }

}
