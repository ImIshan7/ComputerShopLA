package lk.ijse.computershop.bo.custom.impl;

import lk.ijse.computershop.bo.custom.EmployBO;
import lk.ijse.computershop.dao.DAOFactory;
import lk.ijse.computershop.dao.custom.EmployDAO;
import lk.ijse.computershop.dto.EmployDTO;
import lk.ijse.computershop.entity.Employ;


import java.sql.SQLException;
import java.util.ArrayList;

public class EmployBOImpl implements EmployBO {

    EmployDAO employDAO = (EmployDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOY);

    @Override
    public ArrayList<EmployDTO> getAllEmploy() throws SQLException, ClassNotFoundException {
        return employDAO.getAll();

    }

    @Override
    public boolean addEmploy(EmployDTO dto) throws SQLException, ClassNotFoundException {
        return employDAO.add(new Employ(dto.geteMID(),dto.getName(),dto.getAddress(),dto.getContact()));
    }

    @Override
    public boolean updateEmploy(EmployDTO dto) throws SQLException, ClassNotFoundException {
        return employDAO.update(new Employ(dto.geteMID(),dto.getName(),dto.getAddress(),dto.getContact()));
    }

    @Override
    public  EmployDTO searchEmploy(String EMID) throws SQLException, ClassNotFoundException {

        return employDAO.search(EMID);
    }

    @Override
    public boolean deleteEmploy(String EMID) throws SQLException, ClassNotFoundException {
        return employDAO.delete(EMID);
    }

    @Override
    public String generateNewCode() throws SQLException, ClassNotFoundException {
        return employDAO.generateNewID();
    }
}
