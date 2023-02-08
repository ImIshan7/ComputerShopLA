package lk.ijse.computershop.dao.custom;

import lk.ijse.computershop.dao.CrudDAO;
import lk.ijse.computershop.dto.CustomerDTO;
import lk.ijse.computershop.dto.SupOrdersDTO;
import lk.ijse.computershop.entity.SupOrders;

import java.sql.SQLException;

public interface SupOrdersDAO extends CrudDAO<SupOrders> {
    public SupOrdersDTO search(String id) throws SQLException, ClassNotFoundException;
}
