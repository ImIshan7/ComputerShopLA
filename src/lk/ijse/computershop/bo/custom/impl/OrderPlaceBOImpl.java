package lk.ijse.computershop.bo.custom.impl;

import lk.ijse.computershop.bo.custom.OrderPlaceBO;
import lk.ijse.computershop.dao.DAOFactory;
import lk.ijse.computershop.dao.custom.CustomerDAO;
import lk.ijse.computershop.dao.custom.OrderDetailDAO;
import lk.ijse.computershop.dao.custom.OrdersDAO;
import lk.ijse.computershop.dao.custom.ProductDAO;
import lk.ijse.computershop.dto.CustomerDTO;
import lk.ijse.computershop.dto.OrdersDTO;
import lk.ijse.computershop.dto.ProductDTO;
import lk.ijse.computershop.to.Customer;
import lk.ijse.computershop.to.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderPlaceBOImpl implements OrderPlaceBO {


    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);
    OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERS);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer c = customerDAO.search(id);
        return new CustomerDTO(c.getID(),c.getName(),c.getAddress(),c.getContact());
    }

    @Override
    public ProductDTO searchProduct(String PrdID) throws SQLException, ClassNotFoundException {
        Product p = productDAO.search(PrdID);
        return new ProductDTO(p.getPrdID(),p.getName(),p.getUnit_Price(),p.getDescription(),p.getQTY());
    }

    @Override
    public String generateOrderID() throws SQLException, ClassNotFoundException {
        return ordersDAO.generateNewID();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customerEntityData = customerDAO.getAll();
        ArrayList<CustomerDTO> convertTODto = new ArrayList<>();
        for (Customer customer : customerEntityData){
            convertTODto.add(new CustomerDTO(customer.getID(),customer.getName(),customer.getAddress(),customer.getContact()));
        }
        return convertTODto;
    }

    @Override
    public ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException {
        ArrayList<Product> entityTypeData = productDAO.getAll();
        ArrayList<ProductDTO> allData = new ArrayList<>();
        for (Product p : entityTypeData){
            allData.add(new ProductDTO(p.getPrdID(),p.getName(),p.getUnit_Price(),p.getDescription(),p.getQTY()));
        }
        return allData;
    }

    @Override
    public boolean orderPlace(OrdersDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }
}
