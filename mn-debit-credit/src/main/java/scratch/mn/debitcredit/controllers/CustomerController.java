package scratch.mn.debitcredit.controllers;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scratch.mn.debitcredit.service.CustomerService;

@Controller
public class CustomerController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final CustomerService customerService;

    @Inject
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Get("/customer/{customerId}")
    public CustomerDetails findByCustomerId(Long customerId) {
        log.info("Finding {}", customerId);

        return customerService.findCustomerById(customerId);
    }

    @Post("/customer")
    public CustomerDetails register(RegisterCustomerRequest request) {
        log.info("Registering {} {}", request.getGivenName(), request.getSurname());

        return new CustomerDetails(customerService.registerCustomer(request));
    }
}
