package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class OrderInfo {

    @Id
    @GenericGenerator(name="orderId",strategy = "com.ssafy.db.util.OrderIdGenerator")
    @GeneratedValue(generator = "orderId")
    private String id;

    @CreatedDate
    private LocalDateTime registerTime;

    @Column(name = "price")
    private int price;

    @ManyToOne
    private OrderStatus orderStatus;

    @ManyToOne
    private PayType payType;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "orderInfo",cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();

    @ManyToOne
    private Market market;


    public void addOrderItem(OrderItem orderItem){
        orderItemList.add(orderItem);
        orderItem.setOrderInfo(this);
    }



}
