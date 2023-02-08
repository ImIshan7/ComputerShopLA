package lk.ijse.computershop.dao.custom.impl;

import lk.ijse.computershop.dao.SQLUtil;
import lk.ijse.computershop.dao.custom.SupOrderDetailDAO;
import lk.ijse.computershop.entity.SupOrderDetail;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupOrderDetailDAOImpl implements SupOrderDetailDAO {

    public ArrayList<SupOrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(SupOrderDetail entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO SupplierDetail (SupOrderID,PrdID,QTY,Descripion,UnitPrice) VALUES (?,?,?,?,?)",entity.getSupOrderID(),entity.getPrdID(),entity.getQTY(),entity.getDescripion(),entity.getUnitPrice());
    }

    @Override
    public boolean update(SupOrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public SupOrderDetail search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
