package lk.ijse.computershop.dao.custom;

import lk.ijse.computershop.dao.SuperDAO;
import lk.ijse.computershop.to.OrderDetail;

import java.sql.SQLException;

public interface OrderDetailDAO extends SuperDAO {
    boolean add(OrderDetail entity) throws SQLException, ClassNotFoundException;

    boolean update(OrderDetail entity) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    OrderDetail search(String id) throws SQLException, ClassNotFoundException;
}
