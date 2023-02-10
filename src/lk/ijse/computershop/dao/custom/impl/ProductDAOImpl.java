package lk.ijse.computershop.dao.custom.impl;

import lk.ijse.computershop.dao.SQLUtil;
import lk.ijse.computershop.dao.custom.ProductDAO;
import lk.ijse.computershop.dto.CustomerDTO;
import lk.ijse.computershop.dto.ProductDTO;
import lk.ijse.computershop.entity.Product;
import lk.ijse.computershop.view.tm.CustomerTm;
import lk.ijse.computershop.view.tm.ProductTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public ArrayList getAll() throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM Product;" ;
//        ResultSet resultSet=CrudUtil.execute(sql);
        ResultSet resultSet=SQLUtil.execute(sql);
        ArrayList<ProductTm> arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(new ProductTm(resultSet.getString(1),resultSet.getString(2),
                    resultSet.getDouble(3),resultSet.getString(4),resultSet.getInt(5)));
        }
        return arrayList;
    }

    @Override
    public boolean add(Product entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Product(PrdID, Name, UnitPrice, Descripion, QTY) VALUES(?, ?, ?, ?, ?)",
                entity.getPrdID(),entity.getName(),entity.getUnit_Price(),entity.getDescription(),entity.getQty());
    }

    @Override
    public boolean update(Product entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Product set Name = ?, UnitPrice = ?,  Descripion = ?, QTY = ? WHERE PrdID = ?",
                entity.getName(),entity.getUnit_Price(),entity.getDescription(),entity.getQty(),entity.getPrdID());
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
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Product WHERE PrdID= ?",id);
    }

    @Override
    public ProductDTO search(String id) throws SQLException, ClassNotFoundException {
         /*ResultSet rst = SQLUtil.execute("SELECT  * FROM customer WHERE CID = ?", id + "");
        rst.next();

        return new Customer(id + "", rst.getString("CID"), rst.getString("name"),
                rst.getString("address"), rst.getString("contact"));*/

        String sql = "SELECT  * FROM Product WHERE PrdID = ?";
//        ResultSet result = CrudUtil.execute(sql, id);
        ResultSet result = SQLUtil.execute(sql, id);

        if (result.next()) {
            return new ProductDTO(
                    result.getString(1),
                    result.getString(2),
                    result.getDouble(3),
                    result.getString(4),
                    result.getInt(5)
            );
        }
        return null;
    }

    @Override
    public ArrayList<String> loadPrdIDs() throws SQLException, ClassNotFoundException {
        String sql = "SELECT PrdID FROM Product";
//        ResultSet result = CrudUtil.execute(sql);
        ResultSet result = SQLUtil.execute(sql);

        ArrayList<String> iIDList = new ArrayList<>();

        while (result.next()) {
            iIDList.add(result.getString(1));
        }
        return iIDList;
    }
    }



