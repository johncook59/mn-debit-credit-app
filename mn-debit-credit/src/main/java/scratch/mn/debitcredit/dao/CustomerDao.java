package scratch.mn.debitcredit.dao;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import scratch.mn.debitcredit.domain.Customer;

import java.util.Optional;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {
    @Query(value = "select c.* " +
            "from customer c " +
            "join customer_account ca on ca.customer_id = c.id " +
            "join account a on ca.account_id = a.id " +
            "where c.id = :customerId " +
            "and a.id = :accountId",
            nativeQuery = true)
    Optional<Customer> findByIdAndAccountId(Long customerId, Long accountId);
}
