package scratch.mn.debitcredit.controllers;

import io.micronaut.core.annotation.Introspected;
import scratch.mn.debitcredit.domain.Account;
import scratch.mn.debitcredit.domain.Customer;

import java.util.List;
import java.util.stream.Collectors;

@Introspected
public class CustomerDetails {

    public CustomerDetails(Customer customer) {
        this.id = customer.getId();
        this.givenName = customer.getGivenName();
        this.surname = customer.getSurname();
        this.emailAddress = customer.getEmailAddress();
        this.accounts = customer.getAccounts().stream()
            .map(Account::getId)
            .collect(Collectors.toList());
    }

    private Long id;
    private String givenName;
    private String surname;
    private String emailAddress;
    private List<Long> accounts;

    public Long getId() {
        return id;
    }

    public void setBid(Long id) {
        this.id = id;
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

    public List<Long> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Long> accounts) {
        this.accounts = accounts;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
