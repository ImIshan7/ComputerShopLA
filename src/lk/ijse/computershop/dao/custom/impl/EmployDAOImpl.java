package lk.ijse.computershop.dao.custom.impl;
import lk.ijse.computershop.dao.SQLUtil;
import lk.ijse.computershop.dao.custom.EmployDAO;
import lk.ijse.computershop.to.Employ;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployDAOImpl implements EmployDAO {
    @Override
    public ArrayList<Employ> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employ> allEmploy = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Employ");
        while (rst.next()){
         allEmploy.add(new Employ(rst.getString("EMID"), rst.getString("Name"), rst.getString("Address"), rst.getString("Contact"))) ;
        }
        return allEmploy ;
    }

    @Override
    public boolean add(Employ entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Employ(EMID,Name,Address,Contact) VALUES(?,?,?,?)",entity.getEMID(),entity.getName(),entity.getAddress(),entity.getContact());
    }

    @Override
    public boolean update(Employ entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Employ set Name = ?, Address = ?,  Contact = ? WHERE EMID = ?",entity.getName(),entity.getAddress(),entity.getContact(),entity.getEMID());
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
    public Employ search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Employ WHERE EMID=?",id+"");
        rst.next();
        return new Employ(id +"", rst.getString("Name"),rst.getString("Address"));
    }
}
