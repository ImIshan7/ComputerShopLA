package lk.ijse.computershop.bo.custom;

import lk.ijse.computershop.bo.SuperBO;
import lk.ijse.computershop.dto.CustomerDTO;
import lk.ijse.computershop.dto.OrdersDTO;
import lk.ijse.computershop.dto.ProductDTO;
import rex.utils.S;


import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderPlaceBO extends SuperBO {

    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException;
    public ProductDTO searchProduct(String PrdID) throws SQLException, ClassNotFoundException;
    public String generateOrderID() throws SQLException, ClassNotFoundException;
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
    public ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException;
    public boolean orderPlace(OrdersDTO dto) throws SQLException, ClassNotFoundException;

    public ProductDTO findProduct(String PrdID)throws SQLException, ClassNotFoundException;
    public String generateNextOID() throws SQLException, ClassNotFoundException;
}
