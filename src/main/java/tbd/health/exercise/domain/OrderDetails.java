package tbd.health.exercise.domain;

import tbd.health.exercise.domain.enums.Status;

import javax.persistence.*;

@Entity
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Order.class)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
