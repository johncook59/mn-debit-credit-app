package scratch.mn.debitcredit.controllers;

import io.micronaut.core.annotation.Introspected;

import java.math.BigDecimal;

@Introspected
public class BalanceDetails {

    private Long accountId;
    private BigDecimal balance;
    public BalanceDetails(Long accountId, BigDecimal balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
