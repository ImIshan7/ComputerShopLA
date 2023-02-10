package lk.ijse.computershop.dao.custom.impl;

import lk.ijse.computershop.dao.SQLUtil;
import lk.ijse.computershop.dao.custom.SupOrdersDAO;
import lk.ijse.computershop.dto.CustomerDTO;
import lk.ijse.computershop.dto.SupOrdersDTO;
import lk.ijse.computershop.entity.SupOrders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupOrdersDAOImpl implements SupOrdersDAO {
    @Override
    public ArrayList<SupOrders> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(SupOrders entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO SupOrders (SupOrderID,SupID,Date) VALUES (?,?,?)",entity.getSupOrderID(),entity.getSupID(),entity.getDate());
    }

    @Override
    public boolean update(SupOrders entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT SupOrderID FROM SupOrders ORDER BY LIMIT 1;");
        return rst.next() ? String.format("SupOrderID-%03d",(rst.getString("SupOrderID").replace("SO-",""))+1) : "SO-001";
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public SupOrdersDTO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
