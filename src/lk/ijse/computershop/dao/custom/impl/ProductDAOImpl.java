package lk.ijse.computershop.dao.custom.impl;

import lk.ijse.computershop.dao.SQLUtil;
import lk.ijse.computershop.dao.custom.ProductDAO;
import lk.ijse.computershop.to.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public ArrayList<Product> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Product> allProduct = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Product");
        while (rst.next()){

            allProduct.add(new Product(rst.getString("PrdID"),rst.getString("Name"), Double.valueOf(rst.getString("UnitPrice")),rst.getString("Descripion"), Integer.parseInt(rst.getString("QTY"))));

        }
        return allProduct;
    }

    @Override
    public boolean add(Product entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Product(PrdID, Name, UnitPrice, Descripion, QTY) VALUES(?, ?, ?, ?, ?)",entity.getPrdID(),entity.getName(),entity.getUnit_Price(),entity.getDescription(),entity.getQTY());
    }

    @Override
    public boolean update(Product entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Product set Name = ?, UnitPrice = ?,  Descripion = ?, QTY = ? WHERE PrdID = ?",entity.getName(),entity.getUnit_Price(),entity.getDescription(),entity.getQTY(),entity.getPrdID());
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT PrdID FROM Product ORDER BY PrdID DESC LIMIT 1;");
        if (rst.next()){
            String PrdID = rst.getString("PrdID");
            int newProductID = Integer.parseInt(PrdID.replace("P00-","")) + 1;
            return String.format("E00-%3d",newProductID);
        }else
            return "P00-001";
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Product WHERE PrdID= ?",id);
    }

    @Override
    public Product search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Product WHERE PrdID=?",id+"");
        rst.next();
        return new Product(id +"", rst.getString("Name"), Double.valueOf(rst.getString("UnitPrice")),rst.getString("Descripion"), Integer.parseInt(rst.getString("QTY")));
    }
    }

