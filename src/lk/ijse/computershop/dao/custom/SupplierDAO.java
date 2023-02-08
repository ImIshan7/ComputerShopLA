package lk.ijse.computershop.dao.custom;

import lk.ijse.computershop.dao.CrudDAO;
import lk.ijse.computershop.dto.SupOrdersDTO;
import lk.ijse.computershop.dto.SupplierDTO;
import lk.ijse.computershop.entity.Supplier;

import java.sql.SQLException;

public interface SupplierDAO extends CrudDAO<Supplier> {
    public SupplierDTO search(String id) throws SQLException, ClassNotFoundException;

}
