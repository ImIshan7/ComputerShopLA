package lk.ijse.computershop.bo.custom.impl;
import lk.ijse.computershop.bo.custom.SupplierBO;
import lk.ijse.computershop.dao.DAOFactory;
import lk.ijse.computershop.dao.custom.SupplierDAO;
import lk.ijse.computershop.dto.SupplierDTO;
import lk.ijse.computershop.entity.Supplier;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAll();
    }

    @Override
    public boolean addSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.add(new Supplier(dto.getSupid(),dto.getName(), dto.getAddress(), dto.getBrand(), dto.getUnit_Price(), dto.getQty()));
    }

    @Override
    public boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(dto.getSupid(), dto.getName(), dto.getAddress(), dto.getBrand(), dto.getUnit_Price(), dto.getQty()));
    }

    @Override
    public SupplierDTO searchSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.search(id);
    }

    @Override
    public boolean deleteSupplier(String SupID) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(SupID);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return  supplierDAO.generateNewID();
    }
}
