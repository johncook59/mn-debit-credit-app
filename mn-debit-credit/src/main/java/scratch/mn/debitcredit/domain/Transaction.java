package scratch.mn.debitcredit.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", length = 12, nullable = false)
    private Long userId;

    @Version
    private Long version = 0L;

    @Column(name = "direction", nullable = false)
    public String direction;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "processed_dt", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant processed;

    public Transaction() {
    }

    private Transaction(Builder builder) {
        setId(builder.id);
        setUserId(builder.userId);
        setVersion(builder.version);
        setDirection(builder.direction);
        setAmount(builder.amount);
        setBalance(builder.balance);
        setAccountId(builder.accountId);
        setProcessed(builder.processed);
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Instant getProcessed() {
        return processed;
    }

    public void setProcessed(Instant processed) {
        this.processed = processed;
    }

    public static final class Builder {
        private Long id;
        private Long userId;
        private Long version;
        private String direction;
        private BigDecimal amount;
        private BigDecimal balance;
        private Long accountId;
        private Instant processed;

        private Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder userId(Long val) {
            userId = val;
            return this;
        }

        public Builder version(Long val) {
            version = val;
            return this;
        }

        public Builder direction(String val) {
            direction = val;
            return this;
        }

        public Builder amount(BigDecimal val) {
            amount = val;
            return this;
        }

        public Builder balance(BigDecimal val) {
            balance = val;
            return this;
        }

        public Builder accountId(Long val) {
            accountId = val;
            return this;
        }

        public Builder processed(Instant val) {
            processed = val;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }
}
