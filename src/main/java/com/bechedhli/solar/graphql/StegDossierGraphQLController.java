package com.bechedhli.solar.graphql;

import com.bechedhli.solar.entity.StegDossier;
import com.bechedhli.solar.service.StegDossierService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StegDossierGraphQLController {

    private final StegDossierService service;

    @QueryMapping
    public List<StegDossier> stegDossiers() {
        return service.findAll();
    }

    @QueryMapping
    public StegDossier stegDossier(@Argument Long id) {
        return service.findById(id);
    }

    @MutationMapping
    public StegDossier createStegDossier(@Argument StegDossier input) {
        return service.create(input);
    }

    @MutationMapping
    public StegDossier updateStegDossier(@Argument Long id, @Argument StegDossier input) {
        return service.update(id, input);
    }

    @MutationMapping
    public boolean deleteStegDossier(@Argument Long id) {
        return service.delete(id);
    }
}
