package lk.ijse.computershop.dao.custom;
import lk.ijse.computershop.dao.CrudDAO;
import lk.ijse.computershop.dto.CustomerDTO;
import lk.ijse.computershop.entity.Customer;

import java.sql.SQLException;


public interface CustomerDAO extends CrudDAO<Customer> {
    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException;

  //  public static int CustomerCount throws SQLException, ClassNotFoundException;


}
