package lk.ijse.computershop.model;

import lk.ijse.computershop.to.Employ;
import lk.ijse.computershop.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployModel {

    public static boolean save(Employ employ) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO Employ VALUES (?, ?, ?, ?)";
        return CrudUtil.execute(sql, employ.getEMID(), employ.getName(), employ.getAddress(), employ.getContact());
    }

    public static Employ search(String EMID) throws SQLException, ClassNotFoundException {

        String sql = "SELECT  * FROM Employ WHERE EMID = ?";
        ResultSet result = CrudUtil.execute(sql, EMID);

        if (result.next()) {
            return new Employ(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );
        }
        return null;
    }

}


