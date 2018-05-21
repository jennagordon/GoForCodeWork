package dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Tax {

    private String state;
    private BigDecimal taxRate;

    public Tax(String state, BigDecimal taxRate) {
        this.state = state;
        this.taxRate = taxRate;
    }

    public Tax(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tax)) return false;
        Tax tax = (Tax) o;
        return Objects.equals(state, tax.state) &&
                Objects.equals(taxRate, tax.taxRate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(state, taxRate);
    }
}
