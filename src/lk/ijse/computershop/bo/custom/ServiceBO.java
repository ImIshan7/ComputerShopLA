package lk.ijse.computershop.bo.custom;

import lk.ijse.computershop.bo.SuperBO;
import lk.ijse.computershop.dto.ServiceDTO;
import lk.ijse.computershop.dto.SupplierDTO;
import lk.ijse.computershop.to.Service;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ServiceBO extends SuperBO {


    public boolean addService(ServiceDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateService(ServiceDTO dto) throws SQLException, ClassNotFoundException;
    public Service searchService(String SerID) throws SQLException, ClassNotFoundException;
}
