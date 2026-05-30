package app.bechedhli.solar.controller;

import app.bechedhli.solar.entity.Piece;
import app.bechedhli.solar.repository.PieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final PieceRepository pieceRepository;

    @Autowired
    public StockController(PieceRepository pieceRepository) {
        this.pieceRepository = pieceRepository;
    }

    @GetMapping
    public List<Piece> getAll() {
        return pieceRepository.findAll();
    }

    @GetMapping("/{id}")
    public Piece getById(@PathVariable Long id) {
        return pieceRepository.findById(id).orElseThrow(() -> new RuntimeException("Piece not found: " + id));
    }

    @PostMapping
    public Piece create(@RequestBody Piece piece) {
        if (piece.getNom() == null) piece.setNom(piece.getName());
        return pieceRepository.save(piece);
    }

    @PutMapping("/{id}")
    public Piece update(@PathVariable Long id, @RequestBody Piece piece) {
        return pieceRepository.findById(id)
                .map(existing -> {
                    if (piece.getName() != null) existing.setName(piece.getName());
                    if (piece.getCategory() != null) existing.setCategory(piece.getCategory());
                    if (piece.getQty() != null) existing.setQty(piece.getQty());
                    if (piece.getMinQty() != null) existing.setMinQty(piece.getMinQty());
                    if (piece.getPrice() != null) existing.setPrice(piece.getPrice());
                    if (piece.getSupplier() != null) existing.setSupplier(piece.getSupplier());
                    if (piece.getLocation() != null) existing.setLocation(piece.getLocation());
                    return pieceRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Piece not found: " + id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pieceRepository.deleteById(id);
    }
}