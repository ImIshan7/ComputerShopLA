package lk.ijse.computershop.dao.custom;

import lk.ijse.computershop.dao.CrudDAO;
import lk.ijse.computershop.dto.CustomerDTO;
import lk.ijse.computershop.dto.EmployDTO;
import lk.ijse.computershop.entity.Employ;

import java.sql.SQLException;

public interface EmployDAO extends CrudDAO<Employ> {

    public EmployDTO search(String id) throws SQLException, ClassNotFoundException;

}
