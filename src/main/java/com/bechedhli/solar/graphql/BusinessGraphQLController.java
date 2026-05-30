package com.bechedhli.solar.graphql;

import com.bechedhli.solar.entity.DeliveryNote;
import com.bechedhli.solar.entity.Invoice;
import com.bechedhli.solar.service.BusinessOperationService;
import com.bechedhli.solar.service.DashboardMetrics;
import com.bechedhli.solar.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class BusinessGraphQLController {

    private final DashboardService dashboardService;
    private final BusinessOperationService businessOperationService;

    @QueryMapping
    public DashboardMetrics dashboardMetrics() {
        return dashboardService.computeMetrics();
    }

    @MutationMapping
    public DeliveryNote deliverBL(@Argument String id) {
        return businessOperationService.deliverBL(id);
    }

    @MutationMapping
    public Invoice generateInvoiceFromBL(@Argument String blId) {
        return businessOperationService.generateInvoiceFromBL(blId);
    }
}
