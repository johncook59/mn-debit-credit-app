package scratch.mn.debitcredit.service;

import scratch.mn.debitcredit.domain.Transaction;

import java.math.BigDecimal;

public interface TellerService {
    Transaction credit(Long customerId, Long accountId, BigDecimal amount);

    Transaction debit(Long customerId, Long accountId, BigDecimal amount);

    BigDecimal balance(Long customerId, Long accountId);
}
