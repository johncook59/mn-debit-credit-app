package scratch.mn.debitcredit.dao;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import scratch.mn.debitcredit.domain.Account;

import java.util.Optional;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {
    @Query(value = "SELECT * FROM account WHERE id = :id FOR NO KEY UPDATE", nativeQuery = true)
    Optional<Account> findAndLockById(Long id);
}
