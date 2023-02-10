package lk.ijse.computershop.bo.custom.impl;
import lk.ijse.computershop.bo.custom.ProductBO;
import lk.ijse.computershop.dao.DAOFactory;
import lk.ijse.computershop.dao.custom.ProductDAO;
import lk.ijse.computershop.dto.ProductDTO;
import lk.ijse.computershop.entity.Product;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBOImpl implements ProductBO {

    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);

    @Override
    public ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException {
        return productDAO.getAll();
    }

    @Override
    public boolean addProduct(ProductDTO dto) throws SQLException, ClassNotFoundException {
        return productDAO.add(new Product(dto.getPrdID(), dto.getName(), dto.getUnit_Price(), dto.getDescription(),dto.getQty()));
    }

    @Override
    public boolean updateProduct(ProductDTO dto) throws SQLException, ClassNotFoundException {
        return productDAO.update(new Product(dto.getPrdID(),dto.getName(),dto.getUnit_Price(),dto.getDescription(),dto.getQty()));
    }

    @Override
    public ProductDTO searchProduct(String PrdID) throws SQLException, ClassNotFoundException {
        return productDAO.search(PrdID);
    }

    @Override
    public boolean deleteProduct(String PrdID) throws SQLException, ClassNotFoundException {
        return productDAO.delete(PrdID);
    }

    @Override
    public String generateNewCode() throws SQLException, ClassNotFoundException {
        return productDAO.generateNewID();
    }

    @Override
    public ArrayList<String> loadPrdIDs() throws SQLException, ClassNotFoundException {
        return productDAO.loadPrdIDs();
    }
}
