package s24.macroapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Food {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private double quantity;
    private double kcal;

    public Food(String name, double quantity, double kcal) {
        this.name = name;
        this.quantity = quantity;
        this.kcal = kcal;
    }

    public Food() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }
}
