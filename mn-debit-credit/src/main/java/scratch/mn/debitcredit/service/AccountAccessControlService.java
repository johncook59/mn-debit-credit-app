package scratch.mn.debitcredit.service;

import scratch.mn.debitcredit.domain.Account;

public interface AccountAccessControlService {
    default boolean canCredit(Long customerId, Account account) {
        return true;
    }

    boolean canDebit(Long customerId, Account account);

    boolean canReadBalance(Long customerId, Account account);
}
