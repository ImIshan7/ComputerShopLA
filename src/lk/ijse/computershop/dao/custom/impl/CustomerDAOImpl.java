package lk.ijse.computershop.dao.custom.impl;

import lk.ijse.computershop.dao.SQLUtil;
import lk.ijse.computershop.dao.custom.CustomerDAO;
import lk.ijse.computershop.to.Customer;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        while (rst.next()){
            Customer customer = new Customer(rst.getString("CusID"),rst.getString("Name"),rst.getString("Address"),rst.getString("Contact"));
        }
        return allCustomers;
    }

    @Override
    public boolean add(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil. execute("INSERT INTO Customer(CusID,Name,Address,Contact) VALUES (?, ?, ?, ?)",entity.getID(),entity.getName(),entity.getAddress(),entity.getContact());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer set Name = ?, Address = ?, Contact = ? WHERE CusID = ?",entity.getName(),entity.getAddress(),entity.getContact(),entity.getID());
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT CusID FOM Customer ORDER BY CusID DESC LIMIT 1;");
            if (rst.next()){
                String CusID = rst.getString("CusID");
                int newCustomerID = Integer.parseInt(CusID.replace("C00-","")) + 1;
                return String.format("C00-%3d",newCustomerID);
            }else
                return "C00-001";
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM Customer WHERE CusID= ?",id);
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE CusID=?",id+"");
        rst.next();
        return new Customer(id +"", rst.getString("Name"),rst.getString("Address"));
    }
}
