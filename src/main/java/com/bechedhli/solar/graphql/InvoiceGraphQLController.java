package com.bechedhli.solar.graphql;

import com.bechedhli.solar.entity.Invoice;
import com.bechedhli.solar.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class InvoiceGraphQLController {

    private final InvoiceService service;

    @QueryMapping
    public List<Invoice> invoices() {
        return service.findAll();
    }

    @QueryMapping
    public Invoice invoice(@Argument String id) {
        return service.findById(id);
    }

    @MutationMapping
    public Invoice createInvoice(@Argument Invoice input) {
        return service.create(input);
    }

    @MutationMapping
    public Invoice updateInvoice(@Argument String id, @Argument Invoice input) {
        return service.update(id, input);
    }

    @MutationMapping
    public boolean deleteInvoice(@Argument String id) {
        return service.delete(id);
    }
}
