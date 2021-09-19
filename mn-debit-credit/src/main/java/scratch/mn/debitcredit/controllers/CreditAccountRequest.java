package scratch.mn.debitcredit.controllers;

import io.micronaut.core.annotation.Introspected;

import java.math.BigDecimal;

@Introspected
public class CreditAccountRequest {
    private Long accountId;
    private BigDecimal amount;

    public Long getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
