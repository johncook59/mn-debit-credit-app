package scratch.mn.debitcredit.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import scratch.mn.debitcredit.domain.Account;

@Singleton
class DefaultAccountAccessControlService implements AccountAccessControlService {

    private final CustomerService customerService;

    @Inject
    DefaultAccountAccessControlService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public boolean canDebit(Long customerId, Account account) {
        return isCustomerAccountOwner(customerId, account);
    }

    @Override
    public boolean canReadBalance(Long customerId, Account account) {
        return isCustomerAccountOwner(customerId, account);
    }

    private boolean isCustomerAccountOwner(Long customerId, Account account) {
        return customerService.isAccountOwner(customerId, account.getId());
    }
}
