package lk.ijse.computershop.bo.custom.impl;

import lk.ijse.computershop.bo.custom.EmployBO;
import lk.ijse.computershop.dao.DAOFactory;
import lk.ijse.computershop.dao.custom.EmployDAO;
import lk.ijse.computershop.dto.EmployDTO;
import lk.ijse.computershop.to.Employ;


import java.sql.SQLException;
import java.util.ArrayList;

public class EmployBOImpl implements EmployBO {

    EmployDAO employDAO = (EmployDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOY);

    @Override
    public ArrayList<EmployDTO> getAllEmploy() throws SQLException, ClassNotFoundException {
            ArrayList<Employ> allEmploy = employDAO.getAll();
            ArrayList<EmployDTO> allData = new ArrayList<>();
            for ( Employ e : allEmploy){
                allData.add(new EmployDTO(e.getEMID(),e.getName(),e.getAddress(),e.getContact()));
            }
            return allData;
    }

    @Override
    public boolean addEmploy(EmployDTO dto) throws SQLException, ClassNotFoundException {
        return employDAO.add(new Employ(dto.getEMID(),dto.getName(),dto.getAddress(),dto.getContact()));
    }

    @Override
    public boolean updateEmploy(EmployDTO dto) throws SQLException, ClassNotFoundException {
        return employDAO.update(new Employ(dto.getEMID(),dto.getName(),dto.getAddress(),dto.getContact()));
    }

    @Override
    public  Employ searchEmploy(String EMID) throws SQLException, ClassNotFoundException {
        //Employ e = employDAO.search(EMID);
       // return employDAO.search(e.getEMID(),e.getName(),e.getAddress(),e.getContact());

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
