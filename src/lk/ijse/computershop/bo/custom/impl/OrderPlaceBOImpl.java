package lk.ijse.computershop.bo.custom.impl;

import lk.ijse.computershop.bo.custom.OrderPlaceBO;
import lk.ijse.computershop.dao.DAOFactory;
import lk.ijse.computershop.dao.custom.CustomerDAO;
import lk.ijse.computershop.dao.custom.OrderDetailDAO;
import lk.ijse.computershop.dao.custom.OrdersDAO;
import lk.ijse.computershop.dao.custom.ProductDAO;
import lk.ijse.computershop.db.DBConnection;
import lk.ijse.computershop.dto.CustomerDTO;
import lk.ijse.computershop.dto.OrderDetailDTO;
import lk.ijse.computershop.dto.OrdersDTO;
import lk.ijse.computershop.dto.ProductDTO;
import lk.ijse.computershop.entity.Customer;
import lk.ijse.computershop.entity.OrderDetail;
import lk.ijse.computershop.entity.Orders;
import lk.ijse.computershop.entity.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderPlaceBOImpl implements OrderPlaceBO {


    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);
    OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERS);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
       /* CustomerDTO c = customerDAO.search(id);
        return new CustomerDTO(c.getId(),c.getName(),c.getAddress(),c.getContact());*/

        return null;
    }

    @Override
    public ProductDTO searchProduct(String PrdID) throws SQLException, ClassNotFoundException {
//        Product p = productDAO.(PrdID);
//        return new ProductDTO(p.getPrdID(),p.getName(),p.getUnit_Price(),p.getDescription(),p.getQTY());
        return null;
    }

    @Override
    public String generateOrderID() throws SQLException, ClassNotFoundException {
        return ordersDAO.generateNewID();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
       /* ArrayList<Customer> customerEntityData = customerDAO.getAll();
        ArrayList<CustomerDTO> convertTODto = new ArrayList<>();
        for (Customer customer : customerEntityData){
            convertTODto.add(new CustomerDTO(customer.getID(),customer.getName(),customer.getAddress(),customer.getContact()));
        }
        return convertTODto;*/
        return null;
    }

    @Override
    public ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException {
       /* ArrayList<Product> entityTypeData = productDAO.getAll();
        ArrayList<ProductDTO> allData = new ArrayList<>();
        for (Product p : entityTypeData){
            allData.add(new ProductDTO(p.getPrdID(),p.getName(),p.getUnit_Price(),p.getDescription(),p.getQty()));
        }
        return allData;*/
        return null;
    }

    public ProductDTO findProduct(String PrdID) throws SQLException, ClassNotFoundException{
        return null;
    }

    @Override
    public boolean orderPlace(OrdersDTO dto) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            boolean b1 = ordersDAO.exist(dto.getOrderId());
//            if order id already exist
            if (b1) {
                return false;
            }

            connection.setAutoCommit(false);

            //Save the Order to the order table
            boolean b2 = ordersDAO.add(new Orders(dto.getOrderId(), dto.getCusId(), dto.getDescription(),dto.getDate()));
            if (!b2) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            for (OrderDetailDTO d : dto.getOrderDetailDTO()) {
                OrderDetail orderDetail = new OrderDetail(d.getOrderID(), d.getPrdID(), d.getPrice(), d.getQty());
                boolean b3 = orderDetailDAO.add(orderDetail);
                if (!b3) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
                //Search & Update Item
                ProductDTO item = findProduct(d.getPrdID());
                item.setQty(item.getQty() - d.getQty());

                //update item
                boolean b = productDAO.update(new Product(item.getPrdID(), item.getName(), item.getUnit_Price(), item.getDescription(),item.getQty()));

                if (!b) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    public String generateNextOID() throws SQLException, ClassNotFoundException{
        return ordersDAO.generateNextOID();
    }

}
