package scratch.mn.debitcredit.controllers;

import io.micronaut.core.annotation.Introspected;
import scratch.mn.debitcredit.domain.Transaction;

import java.math.BigDecimal;
import java.time.Instant;

@Introspected
public class TransactionDetails {

    public TransactionDetails(Transaction transaction) {
        id = transaction.getId();
        direction = transaction.getDirection();
        amount = transaction.getAmount();
        userId = transaction.getUserId();
        balance = transaction.getBalance();
        accountId = transaction.getAccountId();
        processed = transaction.getProcessed();
    }

    private Long id;

    private String direction;

    private BigDecimal amount;

    private BigDecimal balance;

    private Long userId;

    private Long accountId;

    private Instant processed;

    public Long getId() {
        return id;
    }

    public String getDirection() {
        return direction;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public Instant getProcessed() {
        return processed;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setProcessed(Instant processed) {
        this.processed = processed;
    }
}
