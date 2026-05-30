package com.bechedhli.solar.graphql;

import com.bechedhli.solar.entity.StockItem;
import com.bechedhli.solar.service.StockItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StockItemGraphQLController {

    private final StockItemService service;

    @QueryMapping
    public List<StockItem> stockItems() {
        return service.findAll();
    }

    @QueryMapping
    public StockItem stockItem(@Argument Long id) {
        return service.findById(id);
    }

    @MutationMapping
    public StockItem createStockItem(@Argument StockItem input) {
        return service.create(input);
    }

    @MutationMapping
    public StockItem updateStockItem(@Argument Long id, @Argument StockItem input) {
        return service.update(id, input);
    }

    @MutationMapping
    public boolean deleteStockItem(@Argument Long id) {
        return service.delete(id);
    }
}
