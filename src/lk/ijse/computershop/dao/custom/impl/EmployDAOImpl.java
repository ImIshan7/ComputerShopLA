package lk.ijse.computershop.dao.custom.impl;
import lk.ijse.computershop.dao.SQLUtil;
import lk.ijse.computershop.dao.custom.EmployDAO;
import lk.ijse.computershop.dto.EmployDTO;
import lk.ijse.computershop.entity.Employ;
import lk.ijse.computershop.view.tm.EmployTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployDAOImpl implements EmployDAO {
    @Override
    public ArrayList getAll() throws SQLException, ClassNotFoundException {
        String sql="SELECT * FROM Employ;";
//        ResultSet resultSet=CrudUtil.execute(sql);
        ResultSet resultSet=SQLUtil.execute(sql);
        ArrayList<EmployTm> arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(new EmployTm(resultSet.getString(1),resultSet.getString(2),
                    resultSet.getString(3),resultSet.getString(4)));
        }
        return arrayList;
    }

    @Override
    public boolean add(Employ entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Employ(EMID,Name,Address,Contact) VALUES(?,?,?,?)",entity.geteMID(),entity.getName(),entity.getAddress(),entity.getContact());
    }

    @Override
    public boolean update(Employ entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Employ set Name = ?, Address = ?,  Contact = ? WHERE EMID = ?",entity.getName(),entity.getAddress(),entity.getContact(),entity.geteMID());
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT EMID FROM Employ ORDER BY EMID DESC LIMIT 1;");
        if (rst.next()){
            String EMID = rst.getString("EMID");
            int newEmployID = Integer.parseInt(EMID.replace("E00-","")) + 1;
            return String.format("E00-%3d",newEmployID);
        }else
            return "E00-001";

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Employ WHERE EMID= ?",id);
    }

    @Override
    public EmployDTO search(String id) throws SQLException, ClassNotFoundException {
         /*ResultSet rst = SQLUtil.execute("SELECT  * FROM customer WHERE CID = ?", id + "");
        rst.next();

        return new Customer(id + "", rst.getString("CID"), rst.getString("name"),
                rst.getString("address"), rst.getString("contact"));*/

        String sql = "SELECT  * FROM Employ WHERE EMID = ?";
//        ResultSet result = CrudUtil.execute(sql, id);
        ResultSet result = SQLUtil.execute(sql, id);

        if (result.next()) {
            return new EmployDTO(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );
        }
        return null;
    }
    }

