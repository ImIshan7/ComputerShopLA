package lk.ijse.computershop.model;

import lk.ijse.computershop.to.Customer;
import lk.ijse.computershop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public static boolean save(Customer customer) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO Customer VALUES (?, ?, ?, ?)";
        return CrudUtil.execute(sql, customer.getID(), customer.getName(), customer.getAddress(), customer.getContact());
    }

    public static Customer search(String CusID) throws SQLException, ClassNotFoundException {

        String sql = "SELECT  * FROM Customer WHERE CusID = ?";
        ResultSet result = CrudUtil.execute(sql, CusID);

        if(result.next()) {
            return new Customer(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );
        }
        return null;
    }

    public static ArrayList<String> loadCusID() throws SQLException, ClassNotFoundException {
        String sql = "SELECT CusID FROM Customer";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> CusIDList = new ArrayList<>();

        while (result.next()) {
            CusIDList.add(result.getString(1));
        }
        return CusIDList;
    }

    public static int CustomerCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) from Customer";
        ResultSet result = CrudUtil.execute(sql);

        if (result.next())
            return result.getInt(1);
        return 0;
    }
}




