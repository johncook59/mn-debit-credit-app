package scratch.mn.debitcredit.controllers;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scratch.mn.debitcredit.service.TellerService;

import java.math.BigDecimal;
import java.util.Objects;

@Controller
class TellerController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final TellerService teller;

    @Inject
    public TellerController(TellerService teller) {
        this.teller = teller;
    }

    @Get(value = "/teller/{customerId}/{accountId}/balance")
    public BalanceDetails balance(Long customerId, Long accountId) {
        log.debug("Requesting balance for account {}", accountId);
        return new BalanceDetails(accountId, teller.balance(customerId, accountId));
    }

    @Put(value = "/teller/{customerId}/credit")
    public TransactionDetails credit(Long customerId, @Body CreditAccountRequest request) {
        log.debug("Requesting {} credit to account {}", request.getAmount(), request.getAccountId());
        validateAmount(request.getAmount());

        return new TransactionDetails(teller.credit(customerId, request.getAccountId(), request.getAmount()));
    }

    @Put(value = "/teller/{customerId}/{accountId}/debit")
    public TransactionDetails debit(Long customerId, Long accountId, DebitAccountRequest request) {
        log.debug("Requesting {} debit from account {}", request.getAmount(), accountId);
        validateAmount(request.getAmount());

        return new TransactionDetails(teller.debit(customerId, accountId, request.getAmount()));
    }

    private void validateAmount(BigDecimal amount) {
        Objects.requireNonNull(amount, "Null amount");
        if (amount.doubleValue() < 0) {
            throw new IllegalArgumentException("amount not greater than zero");
        }
    }
}
