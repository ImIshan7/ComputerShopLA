package lk.ijse.computershop.bo.custom;

import lk.ijse.computershop.bo.SuperBO;
import lk.ijse.computershop.dto.ProductDTO;
import lk.ijse.computershop.dto.SupplierDTO;
import lk.ijse.computershop.entity.SupOrders;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierOrderBO extends SuperBO {

    public SupplierDTO searchSupplier(String id) throws SQLException, ClassNotFoundException;
    public ProductDTO searchProduct(String PrdID) throws SQLException, ClassNotFoundException;
    public String generateSupOrderID() throws SQLException, ClassNotFoundException;
    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException;
    public ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException;
    public boolean suporderPlace(SupOrders dto) throws SQLException, ClassNotFoundException;

}
