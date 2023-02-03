package lk.ijse.computershop.dao.custom.impl;

import lk.ijse.computershop.dao.SQLUtil;
import lk.ijse.computershop.dao.custom.ServiceDAO;
import lk.ijse.computershop.to.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ServiceDAOImpl implements ServiceDAO {

    @Override
    public ArrayList<Service> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Service entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Service(SerID,EMID,Descripion,Price) VALUES(?,?,?,?)",
                entity.getSerID(),entity.getEMID(),entity.getDescripion(),entity.getPrice());
    }

    @Override
    public boolean update(Service entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Service set EMID = ?, Descripion = ?, Price = ? WHERE SerID = ?",
                entity.getEMID(), entity.getDescripion(), entity.getPrice(), entity.getSerID());
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
    public Service search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Service WHERE SerID=?",id+"");
        rst.next();
        return new Service(id +"", rst.getString("EMID"),rst.getString("Descripion"),rst.getString("Price"));
    }

}
