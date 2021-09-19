package scratch.mn.debitcredit.controllers;

import io.micronaut.core.annotation.Introspected;

import java.math.BigDecimal;

@Introspected
public class DebitAccountRequest {
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
