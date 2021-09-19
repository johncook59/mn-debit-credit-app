package scratch.mn.debitcredit.dao;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import scratch.mn.debitcredit.domain.Transaction;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Long> {
}
