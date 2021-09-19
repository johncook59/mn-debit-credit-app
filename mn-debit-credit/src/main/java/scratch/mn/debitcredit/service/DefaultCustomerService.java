package scratch.mn.debitcredit.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import scratch.mn.debitcredit.controllers.CustomerDetails;
import scratch.mn.debitcredit.controllers.RegisterCustomerRequest;
import scratch.mn.debitcredit.dao.CustomerDao;
import scratch.mn.debitcredit.domain.Account;
import scratch.mn.debitcredit.domain.Customer;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collections;

@Singleton
class DefaultCustomerService implements CustomerService {

    private final CustomerDao customerDao;

    @Inject
    DefaultCustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Transactional
    @Override
    public Customer registerCustomer(RegisterCustomerRequest request) {

        Account account = Account.builder()
                .balance(new BigDecimal(request.getInitialBalance()))
                .name("Current")
                .build();
        Customer customer = Customer.builder()
                .givenName(request.getGivenName())
                .surname(request.getSurname())
                .emailAddress(request.getEmailAddress())
                .password(request.getPassword())
                .accounts(Collections.singletonList(account))
                .build();

        return customerDao.save(customer);
    }

    @Transactional
    public CustomerDetails findCustomerById(Long customerBid) {
        return new CustomerDetails(customerDao.findById(customerBid)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Customer %d not found", customerBid))));
    }

    @Override
    @Transactional
    public boolean isAccountOwner(Long customerId, Long accountId) {
        return customerDao.findByIdAndAccountId(customerId, accountId).isPresent();
    }
}
