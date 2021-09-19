package scratch.mn.debitcredit.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import java.util.List;

@Entity
@Table(name = "customer",
        uniqueConstraints = @UniqueConstraint(columnNames = "email_address", name = "uk_email_address"),
        indexes = {
                @Index(name = "idx_customer_email_address", columnList = "email_address")})
public class Customer {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Long version = 0L;
    @Column(length = 20, nullable = false)
    private String password;
    @Column(name = "given_name", length = 40, nullable = false)
    private String givenName;
    @Column(length = 40, nullable = false)
    private String surname;
    @Column(name = "email_address", length = 60, nullable = false)
    private String emailAddress;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "customer_account",
            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
            foreignKey = @ForeignKey(name = "fk_customer_id_customer_account"),
            inverseJoinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseForeignKey = @ForeignKey(name = "fk_account_id_customer_account"))
    private List<Account> accounts;

    public Customer() {
    }

    private Customer(Builder builder) {
        setId(builder.id);
        setVersion(builder.version);
        setPassword(builder.password);
        setGivenName(builder.givenName);
        setSurname(builder.surname);
        setEmailAddress(builder.emailAddress);
        setAccounts(builder.accounts);
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public static final class Builder {
        private Long id;
        private Long version;
        private String password;
        private String givenName;
        private String surname;
        private String emailAddress;
        private List<Account> accounts;

        private Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder version(Long val) {
            version = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder givenName(String val) {
            givenName = val;
            return this;
        }

        public Builder surname(String val) {
            surname = val;
            return this;
        }

        public Builder emailAddress(String val) {
            emailAddress = val;
            return this;
        }

        public Builder accounts(List<Account> val) {
            accounts = val;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
