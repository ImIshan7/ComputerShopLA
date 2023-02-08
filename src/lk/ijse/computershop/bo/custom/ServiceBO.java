package lk.ijse.computershop.bo.custom;

import lk.ijse.computershop.bo.SuperBO;
import lk.ijse.computershop.dto.ProductDTO;
import lk.ijse.computershop.dto.ServiceDTO;
import lk.ijse.computershop.entity.Service;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ServiceBO extends SuperBO {



    public ArrayList<ServiceDTO> getAllService() throws SQLException, ClassNotFoundException;
    public boolean addService(ServiceDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateService(ServiceDTO dto) throws SQLException, ClassNotFoundException;
    public ServiceDTO searchService(String SerID) throws SQLException, ClassNotFoundException;
}
