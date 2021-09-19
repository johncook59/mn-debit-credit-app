package scratch.mn.debitcredit.service;

import scratch.mn.debitcredit.controllers.CustomerDetails;
import scratch.mn.debitcredit.controllers.RegisterCustomerRequest;
import scratch.mn.debitcredit.domain.Customer;

public interface CustomerService {
    Customer registerCustomer(RegisterCustomerRequest request);

    CustomerDetails findCustomerById(Long customerBid);

    boolean isAccountOwner(Long customerId, Long accountId);
}
