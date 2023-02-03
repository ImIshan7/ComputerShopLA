package lk.ijse.computershop.bo.custom;
import lk.ijse.computershop.bo.SuperBO;
import lk.ijse.computershop.dto.EmployDTO;
import lk.ijse.computershop.to.Employ;


import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployBO extends SuperBO {

    public ArrayList<EmployDTO> getAllEmploy() throws SQLException, ClassNotFoundException;
    public boolean addEmploy(EmployDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateEmploy(EmployDTO dto) throws SQLException, ClassNotFoundException;
    public Employ searchEmploy(String EMID) throws SQLException, ClassNotFoundException;
    public boolean deleteEmploy(String EMID) throws SQLException, ClassNotFoundException;
    public String generateNewCode() throws SQLException, ClassNotFoundException;

}
