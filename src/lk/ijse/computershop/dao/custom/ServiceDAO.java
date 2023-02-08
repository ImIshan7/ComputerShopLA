package lk.ijse.computershop.dao.custom;

import lk.ijse.computershop.dao.CrudDAO;
import lk.ijse.computershop.dto.ServiceDTO;
import lk.ijse.computershop.dto.SupplierDTO;
import lk.ijse.computershop.entity.Service;

import java.sql.SQLException;


public interface ServiceDAO extends CrudDAO<Service> {

    public ServiceDTO search(String id) throws SQLException, ClassNotFoundException;
}
