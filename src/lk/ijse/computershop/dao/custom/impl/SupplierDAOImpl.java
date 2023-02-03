package lk.ijse.computershop.dao.custom.impl;
import lk.ijse.computershop.dao.SQLUtil;
import lk.ijse.computershop.dao.custom.SupplierDAO;
import lk.ijse.computershop.to.Supplier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> allSupplier = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Supplier");
        while (rst.next()){
            Supplier supplier = new Supplier(rst.getString("SupID"),rst.getString("Name"),rst.getString("Address"),rst.getString("Brand"),rst.getString("Unit_Price"),rst.getString("QTY"));
        }
        return allSupplier ;

    }

    @Override
    public boolean add(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil. execute("INSERT INTO Supplier(SupID, Name, Address, Brand, Unit_Price, QTY) VALUES (?, ?, ?, ?, ?, ?)",entity.getSupID(),entity.getName(),entity.getAddress(),entity.getBrand(),entity.getUnit_Price(),entity.getQTY());
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Supplier set Name = ?, Address = ?, Brand = ?, Unit_Price = ?, QTY = ? WHERE SupID = ?",entity.getName(),entity.getAddress(),entity.getBrand(),entity.getUnit_Price(),entity.getQTY(),entity.getSupID());
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
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Supplier WHERE SupID=?",id+"");
        rst.next();
        return new Supplier(id +"", rst.getString("Name"),rst.getString("Address"),rst.getString("Brand"),rst.getString("Unit_Price"),rst.getString("QTY"));
    }
}
