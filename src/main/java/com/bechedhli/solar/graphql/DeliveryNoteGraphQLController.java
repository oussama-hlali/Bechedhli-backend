package com.bechedhli.solar.graphql;

import com.bechedhli.solar.entity.DeliveryNote;
import com.bechedhli.solar.service.DeliveryNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DeliveryNoteGraphQLController {

    private final DeliveryNoteService service;

    @QueryMapping
    public List<DeliveryNote> deliveryNotes() {
        return service.findAll();
    }

    @QueryMapping
    public DeliveryNote deliveryNote(@Argument String id) {
        return service.findById(id);
    }

    @MutationMapping
    public DeliveryNote createDeliveryNote(@Argument DeliveryNote input) {
        return service.create(input);
    }

    @MutationMapping
    public DeliveryNote updateDeliveryNote(@Argument String id, @Argument DeliveryNote input) {
        return service.update(id, input);
    }

    @MutationMapping
    public boolean deleteDeliveryNote(@Argument String id) {
        return service.delete(id);
    }
}
