package lk.ijse.computershop.dao.custom.impl;

import lk.ijse.computershop.dao.SQLUtil;
import lk.ijse.computershop.dao.custom.CustomerDAO;
import lk.ijse.computershop.dto.CustomerDTO;
import lk.ijse.computershop.entity.Customer;
import lk.ijse.computershop.entity.Employ;
import lk.ijse.computershop.view.tm.CustomerTm;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList getAll() throws SQLException, ClassNotFoundException {
        /*ArrayList<Customer> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        while (rst.next()){
            allCustomers.add(new Customer(rst.getString("CusID"), rst.getString("Name"), rst.getString("Address"), rst.getString("Contact"))) ;
        }
        return allCustomers;*/
        String sql="SELECT * FROM Customer;";
//        ResultSet resultSet=CrudUtil.execute(sql);
        ResultSet resultSet=SQLUtil.execute(sql);
        ArrayList<CustomerTm> arrayList=new ArrayList();
        while (resultSet.next()){
            arrayList.add(new CustomerTm(resultSet.getString(1),resultSet.getString(2),
                    resultSet.getString(3),resultSet.getString(4)));
        }
        return arrayList;

    }

    @Override
    public boolean add(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Customer(CusID,Name,Address,Contact) VALUES (?, ?, ?, ?)", entity.getID(), entity.getName(), entity.getAddress(), entity.getContact());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer set Name = ?, Address = ?, Contact = ? WHERE CusID = ?", entity.getName(), entity.getAddress(), entity.getContact(), entity.getID());
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT CusID FOM Customer ORDER BY CusID DESC LIMIT 1;");
        if (rst.next()) {
            String CusID = rst.getString("CusID");
            int newCustomerID = Integer.parseInt(CusID.replace("C00-", "")) + 1;
            return String.format("C00-%3d", newCustomerID);
        } else
            return "C00-001";
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM Customer WHERE CusID= ?", id);
    }

    @Override
    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {

         /*ResultSet rst = SQLUtil.execute("SELECT  * FROM customer WHERE CID = ?", id + "");
        rst.next();

        return new Customer(id + "", rst.getString("CID"), rst.getString("name"),
                rst.getString("address"), rst.getString("contact"));*/

        String sql = "SELECT  * FROM Customer WHERE CusID = ?";
//        ResultSet result = CrudUtil.execute(sql, id);
        ResultSet result = SQLUtil.execute(sql, id);

        if (result.next()) {
            return new CustomerDTO(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4)
            );
        }
        return null;
    }

    @Override
    public ArrayList<String> loadCusIDs() throws SQLException, ClassNotFoundException {
        String sql = "SELECT CusID FROM Customer";
//        ResultSet result = CrudUtil.execute(sql);
        ResultSet result = SQLUtil.execute(sql);

        ArrayList<String> iIDList = new ArrayList<>();

        while (result.next()) {
            iIDList.add(result.getString(1));
        }
        return iIDList;
    }
}