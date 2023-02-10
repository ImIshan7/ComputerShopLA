package lk.ijse.computershop.dto;

import lk.ijse.computershop.entity.OrderDetail;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrdersDTO {

    private String orderId;
    private String cusId;
    private String description;
    private java.sql.Date date;

    private List<OrderDetailDTO> orderDetailDTO;

    public OrdersDTO() {
    }

    public OrdersDTO(String orderId, String cusId, String description, Date date, List<OrderDetailDTO> orderDetailDTO) {
        this.orderId = orderId;
        this.cusId = cusId;
        this.description = description;
        this.date = date;
        this.orderDetailDTO = orderDetailDTO;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<OrderDetailDTO> getOrderDetailDTO() {
        return orderDetailDTO;
    }

    public void setOrderDetailDTO(List<OrderDetailDTO> orderDetailDTO) {
        this.orderDetailDTO = orderDetailDTO;
    }

    @Override
    public String toString() {
        return "OrdersDTO{" +
                "orderId='" + orderId + '\'' +
                ", cusId='" + cusId + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", orderDetailDTO=" + orderDetailDTO +
                '}';
    }
}
