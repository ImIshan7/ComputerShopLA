package lk.ijse.computershop.bo;

import lk.ijse.computershop.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;
    private BOFactory(){
    }

    public static BOFactory getBoFactory(){
        return (boFactory== null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BoTypes{
        CUSTOMER,EMPLOY,OP,PRODUCT,SERVICE,SUPPLIER,SP
    }


    public  SuperBO getBO(BoTypes types){
        switch (types){

            case CUSTOMER:
                return new CustomerBOImpl();

            case EMPLOY:
                return new EmployBOImpl();

            case OP:
                return new OrderPlaceBOImpl();

            case PRODUCT:
                return new ProductBOImpl();

            case SERVICE:
                return new ServiceBOImpl();

            case SUPPLIER:
                return new  SupplierBOImpl();

            case SP:
                return new SupplierOrderBOImpl();
        }
        return null;
    }
    }

