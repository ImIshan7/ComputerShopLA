package lk.ijse.computershop.dao;



import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {

    public ArrayList getAll() throws SQLException, ClassNotFoundException;
    public boolean add(T entity) throws SQLException, ClassNotFoundException;
    public boolean update(T entity) throws SQLException, ClassNotFoundException;
    public String generateNewID() throws SQLException, ClassNotFoundException;
    public boolean exist(String id) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;


}
