package lk.ijse.computershop.dao;


import lk.ijse.computershop.dao.custom.*;
import lk.ijse.computershop.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;
    private DAOFactory(){
    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory== null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,EMPLOY,ORDER_DETAIL,ORDERS,PRODUCT,SERVICE,SUP_ORDER_DETAIL,SUP_ORDERS,SUPPLIER,QUERY_DAO
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types){

            case CUSTOMER :

                return new CustomerDAOImpl();

            case EMPLOY:
                return new EmployDAOImpl();

            case ORDER_DETAIL:
                return new OrderDetailDAOImpl();

            case ORDERS:
                return new OrdersDAOImpl();

            case PRODUCT:
                return new ProductDAOImpl();

            case SERVICE:
                return new ServiceDAOImpl();

            case SUP_ORDER_DETAIL:
                return new SupOrderDetailDAOImpl();

            case SUP_ORDERS:
                return new SupOrdersDAOImpl();

            case SUPPLIER:
                return new SupplierDAOImpl();

            case QUERY_DAO:
                return new QueryDAOImpl();

        }
        return null;
    }
}
