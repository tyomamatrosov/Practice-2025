package com.example.controllers;

import com.example.dto.LotDTO;
import com.example.service.LotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/lots")
public class LotController {

    private final LotService lotService;

    public LotController(LotService lotService) {
        this.lotService = lotService;
    }


    @PostMapping("/add")
    public ResponseEntity<String> addLot(@RequestBody LotDTO lotDTO) {
        lotService.addLot(lotDTO);
        return ResponseEntity.ok("Lot added successfully");
    }


    @GetMapping("/list")
    public ResponseEntity<List<LotDTO>> getAllLots() {
        List<LotDTO> lots = lotService.getAllLots();
        return ResponseEntity.ok(lots);
    }


    @GetMapping("/name/{lotName}")
    public ResponseEntity<List<LotDTO>> getLotsByName(@PathVariable String lotName) {
        List<LotDTO> lots = lotService.getLotsByName(lotName);
        return ResponseEntity.ok(lots);
    }


    @PutMapping("/update/{lotName}")
    public ResponseEntity<String> updateLot(@PathVariable String lotName, @RequestBody LotDTO lotDTO) {
        lotService.updateLotByName(lotName, lotDTO);
        return ResponseEntity.ok("Lot updated successfully");
    }


    @DeleteMapping("/delete/{lotName}")
    public ResponseEntity<String> deleteLot(@PathVariable String lotName) {
        lotService.deleteLotByName(lotName);
        return ResponseEntity.ok("Lot deleted successfully");
    }
}

