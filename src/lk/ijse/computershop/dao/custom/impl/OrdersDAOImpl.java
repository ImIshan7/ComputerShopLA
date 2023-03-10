package lk.ijse.computershop.dao.custom.impl;
import lk.ijse.computershop.dao.SQLUtil;
import lk.ijse.computershop.dao.custom.OrdersDAO;
import lk.ijse.computershop.dto.CustomerDTO;
import lk.ijse.computershop.dto.OrdersDTO;
import lk.ijse.computershop.entity.Orders;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersDAOImpl implements OrdersDAO {
    @Override
    public ArrayList getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Orders entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Orders (OrderID,CusID,Descripion,Date) VALUES (?,?,?,?)",entity.getOrderID(),entity.getCusID(),entity.getDescription(),entity.getDate());
    }

    @Override
    public boolean update(Orders entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
       return null;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT OrderID FROM Orders WHERE OrderID = ?",id);
        return rst.next();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrdersDTO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNextOID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT OrderID FROM Orders ORDER BY LIMIT 1;");
        return rst.next() ? String.format("OrderID-%03d",(rst.getString("OrderID").replace("O-",""))+1) : "O-001";
    }
}
