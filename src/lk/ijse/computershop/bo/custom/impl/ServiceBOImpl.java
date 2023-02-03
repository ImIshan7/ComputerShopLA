package lk.ijse.computershop.bo.custom.impl;
import lk.ijse.computershop.bo.custom.ServiceBO;
import lk.ijse.computershop.dao.DAOFactory;
import lk.ijse.computershop.dao.custom.ServiceDAO;
import lk.ijse.computershop.dto.ServiceDTO;
import lk.ijse.computershop.to.Service;

import java.sql.SQLException;

public class ServiceBOImpl implements ServiceBO {

    ServiceDAO serviceDAO = (ServiceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SERVICE);

    @Override
    public boolean addService(ServiceDTO dto) throws SQLException, ClassNotFoundException {
        return serviceDAO.add(new Service(dto.getSerID(),dto.getEMID(),dto.getDescripion(),dto.getPrice()));
    }

    @Override
    public boolean updateService(ServiceDTO dto) throws SQLException, ClassNotFoundException {
        return serviceDAO.update(new Service(dto.getSerID(), dto.getEMID(), dto.getDescripion(), dto.getPrice()));
    }

    @Override
    public Service searchService(String SerID) throws SQLException, ClassNotFoundException {
        return serviceDAO.search(SerID);
    }
}
