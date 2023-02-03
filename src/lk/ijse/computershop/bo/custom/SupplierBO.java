package lk.ijse.computershop.bo.custom;

import lk.ijse.computershop.bo.SuperBO;
import lk.ijse.computershop.dto.SupplierDTO;
import lk.ijse.computershop.to.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {

    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException;
    public boolean addSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException;
    public Supplier searchSupplier(String id) throws SQLException, ClassNotFoundException;
    public boolean deleteSupplier(String SupID) throws SQLException, ClassNotFoundException;
    public String generateNewID() throws SQLException, ClassNotFoundException;
}
