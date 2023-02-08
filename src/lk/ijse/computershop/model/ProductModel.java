package lk.ijse.computershop.model;
import lk.ijse.computershop.db.DBConnection;
import lk.ijse.computershop.entity.OrderDetail;
import lk.ijse.computershop.entity.Product;
import lk.ijse.computershop.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductModel {

    public static boolean save(Product product) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO Product VALUES (?, ?, ?, ?,?)";
        return CrudUtil.execute(sql,product.getPrdID(), product.getName(),  product.getUnit_Price(),product.getDescription(),product.getQty());
    }

    public static Product search(String PrdID) throws SQLException, ClassNotFoundException {

        String sql = "SELECT  * FROM Product WHERE PrdID = ?";
        ResultSet result = CrudUtil.execute(sql, PrdID);

        if(result.next()) {
            return new Product(
                    result.getString(1),
                    result.getString(2),
                    result.getDouble(3),
                    result.getString(4),
                    result.getInt(5)
            );
        }
        return null;
    }

    public static ArrayList<String> loadPrdID() throws SQLException, ClassNotFoundException {
        String sql = "SELECT PrdID FROM Product";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> ProductList = new ArrayList<>();

        while (result.next()) {
            ProductList.add(result.getString(1));
        }
        return ProductList;
    }

    public static boolean updateQty(ArrayList<OrderDetail > orderDetails) throws SQLException, ClassNotFoundException {
        for (OrderDetail cartDetail : orderDetails) {
            if (!updateQty(cartDetail)){
                return false;
            }
        }
        return true;

    }

        private static boolean updateQty(OrderDetail orderDetail) throws SQLException, ClassNotFoundException {
            PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("UPDATE Product SET QTY = QTY -? WHERE PrdID =?");
            stm.setObject(1, orderDetail.getQTY());
            stm.setObject(2, orderDetail.getPrdID());

            return stm.executeUpdate() > 0;
        }

    public static int ProductCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) from Product";
        ResultSet result = CrudUtil.execute(sql);

        if (result.next())
            return result.getInt(1);
        return 0;
    }


    }

