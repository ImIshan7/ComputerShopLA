package lk.ijse.computershop.model;
import lk.ijse.computershop.to.Service;
import lk.ijse.computershop.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceModel {

    public static boolean save(Service service) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO Service VALUES (?, ?, ?, ?)";
        return CrudUtil.execute(sql,service.getSerID(), service.getEMID(), service.getDescripion(),service.getPrice());
    }

    public static Service search(String SerID) throws SQLException, ClassNotFoundException {

        String sql = "SELECT  * FROM Service WHERE SerID = ?";
        ResultSet result = CrudUtil.execute(sql, SerID);

        if(result.next()) {
            return new Service(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getDouble(4)
            );
        }
        return null;
    }

    public static int ServiceCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) from Service";
        ResultSet result = CrudUtil.execute(sql);

        if (result.next())
            return result.getInt(1);
        return 0;
    }
}

