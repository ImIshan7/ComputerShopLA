package lk.ijse.computershop.dao.custom.impl;
import lk.ijse.computershop.dao.SQLUtil;
import lk.ijse.computershop.dao.custom.SupplierDAO;
import lk.ijse.computershop.dto.SupplierDTO;
import lk.ijse.computershop.entity.Supplier;
import lk.ijse.computershop.view.tm.SupplierTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList getAll() throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM Supplier;";
//        ResultSet resultSet=CrudUtil.execute(sql);
        ResultSet resultSet=SQLUtil.execute(sql);
        ArrayList<SupplierTm> arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(new SupplierTm(resultSet.getString(1),resultSet.getString(2),
                    resultSet.getString(3),resultSet.getString(4),
                    resultSet.getDouble(5),resultSet.getInt(6)));
        }
        return arrayList;

    }

    @Override
    public boolean add(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil. execute("INSERT INTO Supplier(SupID, Name, Address, Brand, Unit_Price, QTY) VALUES (?, ?, ?, ?, ?, ?)",
                entity.getSupid(),entity.getName(),entity.getAddress(),entity.getBrand(),entity.getUnit_Price(),entity.getQty());
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Supplier set Name = ?, Address = ?, Brand = ?, Unit_Price = ?, QTY = ? WHERE SupID = ?",
                entity.getName(),entity.getAddress(),entity.getBrand(),entity.getUnit_Price(),entity.getQty(),entity.getSupid());
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT SupID FROM Supplier ORDER BY SupID DESC LIMIT 1;");
        if (rst.next()){
            String SupID = rst.getString("SupID");
            int newSupplierID = Integer.parseInt(SupID.replace("S00-","")) + 1;
            return String.format("C00-%3d",newSupplierID);
        }else
            return "S00-001";
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Supplier WHERE SupID= ?",id);

    }

    @Override
    public SupplierDTO search(String id) throws SQLException, ClassNotFoundException {
        /*ResultSet rst = SQLUtil.execute("SELECT  * FROM customer WHERE CID = ?", id + "");
        rst.next();

        return new Customer(id + "", rst.getString("CID"), rst.getString("name"),
                rst.getString("address"), rst.getString("contact"));*/

        String sql = "SELECT  * FROM Supplier WHERE SupID = ?";
//        ResultSet result = CrudUtil.execute(sql, id);
        ResultSet result = SQLUtil.execute(sql, id);

        if (result.next()) {
            return new SupplierDTO(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );
        }
        return null;
    }
    }

