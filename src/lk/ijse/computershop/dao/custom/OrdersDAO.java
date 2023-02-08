package lk.ijse.computershop.dao.custom;

import lk.ijse.computershop.dao.CrudDAO;
import lk.ijse.computershop.dto.OrdersDTO;
import lk.ijse.computershop.dto.ProductDTO;
import lk.ijse.computershop.entity.Orders;

import java.sql.SQLException;

public interface OrdersDAO extends CrudDAO<Orders> {
    public OrdersDTO search(String id) throws SQLException, ClassNotFoundException;


}
