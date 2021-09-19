package scratch.mn.debitcredit.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import scratch.mn.debitcredit.dao.AccountDao;
import scratch.mn.debitcredit.dao.TransactionDao;
import scratch.mn.debitcredit.domain.Account;
import scratch.mn.debitcredit.domain.Transaction;
import scratch.mn.debitcredit.domain.TransactionDirection;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;

import static scratch.mn.debitcredit.domain.TransactionDirection.CREDIT;
import static scratch.mn.debitcredit.domain.TransactionDirection.DEBIT;

@Singleton
class DefaultTellerService implements TellerService {

    private final AccountAccessControlService accountAccessControlService;
    private final AccountDao accountDao;
    private final TransactionDao transactionDao;

    @Inject
    DefaultTellerService(AccountAccessControlService accountAccessControlService,
                         AccountDao accountDao,
                         TransactionDao transactionDao) {
        this.accountAccessControlService = accountAccessControlService;
        this.accountDao = accountDao;
        this.transactionDao = transactionDao;
    }

    @Transactional
    @Override
    public Transaction credit(Long customerId, Long accountId, BigDecimal amount) {
        try {
            Account account = accountDao.findAndLockById(accountId)
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Unable to lock account %d", accountId)));

            if (!accountAccessControlService.canCredit(customerId, account)) {
                throw new IllegalArgumentException(String.format("No account for %s", accountId));
            }

            return updateBalance(customerId, account, amount, CREDIT);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    @Override
    public Transaction debit(Long customerId, Long accountId, BigDecimal amount) {
        try {
            Account account = accountDao.findAndLockById(accountId)
                    .orElseThrow(() -> new EntityNotFoundException(String.format("Unable to lock account %d", accountId)));

            if (!accountAccessControlService.canDebit(customerId, account)) {
                throw new IllegalArgumentException(String.format("No account for %s", accountId));
            }

            return updateBalance(customerId, account, amount, DEBIT);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    @Override
    public BigDecimal balance(Long customerId, Long accountId) {
        Account account = accountDao.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Account %d not found", accountId)));

        if (!accountAccessControlService.canReadBalance(customerId, account)) {
            throw new IllegalArgumentException(String.format("No account for %s", accountId));
        }

        return account.getBalance();
    }

    private Transaction updateBalance(Long customerId, Account account, BigDecimal amount, TransactionDirection direction) {
        account.setBalance(account.getBalance().add(direction == CREDIT ? amount : amount.negate()));
        accountDao.save(account);

        Transaction transaction = Transaction.builder()
                .userId(customerId)
                .accountId(account.getId())
                .amount(amount)
                .balance(account.getBalance())
                .direction(direction.name())
                .processed(Instant.now())
                .build();
        return transactionDao.save(transaction);
    }
}
