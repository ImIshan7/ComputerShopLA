package lk.ijse.computershop.dao.custom;

import lk.ijse.computershop.dao.CrudDAO;

import lk.ijse.computershop.dto.CustomerDTO;
import lk.ijse.computershop.dto.ProductDTO;
import lk.ijse.computershop.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDAO extends CrudDAO<Product> {
    public ProductDTO search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<String>loadPrdIDs() throws SQLException, ClassNotFoundException;

}
