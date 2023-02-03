package lk.ijse.computershop.bo.custom.impl;

import lk.ijse.computershop.bo.custom.SupplierOrderBO;
import lk.ijse.computershop.dao.DAOFactory;
import lk.ijse.computershop.dao.custom.ProductDAO;
import lk.ijse.computershop.dao.custom.SupOrderDetailDAO;
import lk.ijse.computershop.dao.custom.SupOrdersDAO;
import lk.ijse.computershop.dao.custom.SupplierDAO;
import lk.ijse.computershop.dto.ProductDTO;
import lk.ijse.computershop.dto.SupplierDTO;
import lk.ijse.computershop.to.Product;
import lk.ijse.computershop.to.SupOrders;
import lk.ijse.computershop.to.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderBOImpl implements SupplierOrderBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);
    SupOrdersDAO supOrdersDAO = (SupOrdersDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUP_ORDERS);
    SupOrderDetailDAO supOrderDetailDAO = (SupOrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUP_ORDER_DETAIL);




    @Override
    public SupplierDTO searchSupplier(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.search(id);
        return new SupplierDTO(supplier.getSupID(),supplier.getName(),supplier.getAddress(),supplier.getBrand(),supplier.getUnit_Price(),supplier.getQTY());
    }

    @Override
    public ProductDTO searchProduct(String PrdID) throws SQLException, ClassNotFoundException {
        Product p = productDAO.search(PrdID);
        return new ProductDTO(p.getPrdID(),p.getName(),p.getUnit_Price(),p.getDescription(),p.getQTY());
    }

    @Override
    public String generateSupOrderID() throws SQLException, ClassNotFoundException {
        return supOrdersDAO.generateNewID();
    }

    @Override
    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> allEntityDTO = supplierDAO.getAll();
        ArrayList<SupplierDTO> allData = new ArrayList<>() ;
        for (SupplierDTO s : allData){
            allEntityDTO.add(new Supplier(s.getSupID(),s.getName(),s.getAddress(),s.getBrand(),s.getUnit_Price(),s.getQTY()));
        }
        return allData;
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
    public boolean suporderPlace(SupOrders dto) throws SQLException, ClassNotFoundException {
        return false;
    }
}
