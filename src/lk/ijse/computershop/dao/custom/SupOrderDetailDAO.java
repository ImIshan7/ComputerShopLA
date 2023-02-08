package lk.ijse.computershop.dao.custom;

import lk.ijse.computershop.dao.SuperDAO;
import lk.ijse.computershop.entity.SupOrderDetail;

import java.sql.SQLException;

public interface SupOrderDetailDAO extends SuperDAO {
    boolean add(SupOrderDetail entity) throws SQLException, ClassNotFoundException;

    boolean update(SupOrderDetail entity) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    SupOrderDetail search(String id) throws SQLException, ClassNotFoundException;
}
