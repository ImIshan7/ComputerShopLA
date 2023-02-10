package lk.ijse.computershop.bo.custom;

import lk.ijse.computershop.bo.SuperBO;
import lk.ijse.computershop.dto.ProductDTO;
import lk.ijse.computershop.entity.Product;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductBO extends SuperBO {
    public ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException;
    public boolean addProduct(ProductDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateProduct(ProductDTO dto) throws SQLException, ClassNotFoundException;
    public ProductDTO searchProduct(String PrdID) throws SQLException, ClassNotFoundException;
    public boolean deleteProduct(String PrdID) throws SQLException, ClassNotFoundException;
    public String generateNewCode() throws SQLException, ClassNotFoundException;

    public ArrayList<String> loadPrdIDs() throws SQLException, ClassNotFoundException;
}
