package lk.ijse.computershop.dao.custom.impl;

import lk.ijse.computershop.dao.SQLUtil;
import lk.ijse.computershop.dao.custom.ServiceDAO;
import lk.ijse.computershop.dto.CustomerDTO;
import lk.ijse.computershop.dto.ServiceDTO;
import lk.ijse.computershop.dto.SupplierDTO;
import lk.ijse.computershop.entity.Service;
import lk.ijse.computershop.view.tm.CustomerTm;
import lk.ijse.computershop.view.tm.ServiceTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ServiceDAOImpl implements ServiceDAO {

    @Override
    public ArrayList getAll() throws SQLException, ClassNotFoundException {

        String sql="SELECT * FROM Service;";
//        ResultSet resultSet=CrudUtil.execute(sql);
        ResultSet resultSet=SQLUtil.execute(sql);
        ArrayList<ServiceTm> arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(new ServiceTm(resultSet.getString(1),resultSet.getString(2),
                    resultSet.getString(3),resultSet.getDouble(4)));
        }
        return arrayList;
    }

    @Override
    public boolean add(Service entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Service(SerID,EMID,Descripion,Price) VALUES(?,?,?,?)",
                entity.getSerID(),entity.getEmID(),entity.getDescripion(),entity.getPrice());
    }

    @Override
    public boolean update(Service entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Service set EMID = ?, Descripion = ?, Price = ? WHERE SerID = ?",
                entity.getEmID(), entity.getDescripion(), entity.getPrice(), entity.getSerID());
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
    public ServiceDTO search(String id) throws SQLException, ClassNotFoundException {
        /*ResultSet rst = SQLUtil.execute("SELECT  * FROM customer WHERE CID = ?", id + "");
        rst.next();

        return new Customer(id + "", rst.getString("CID"), rst.getString("name"),
                rst.getString("address"), rst.getString("contact"));*/

        String sql = "SELECT  * FROM Service WHERE SerID = ?";
//        ResultSet result = CrudUtil.execute(sql, id);
        ResultSet result = SQLUtil.execute(sql, id);

        if (result.next()) {
            return new ServiceDTO(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getDouble(4)

            );
        }
        return null;
    }

}
