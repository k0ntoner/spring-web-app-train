package com.example.demo.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import com.example.demo.models.Position;

import com.example.demo.services.PositionService;



import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/positions")
public class PositionController {
    @Autowired
    private PositionService positionService;
    @PostMapping
    public ResponseEntity<Position> addPosition(@RequestBody Position position) {
        Position newPosition = positionService.addPosition(position);
        if(newPosition==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(position);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosition(@PathVariable Long id){
        positionService.deletePositionById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<Position>> getAllPositions() {
        List<Position> positions= positionService.getAllPositions();
        if(positions==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(positions);      
    }
    @GetMapping("/{id}")
    public ResponseEntity<Position> getPositionById(@PathVariable Long id) {
        return positionService.getPositionById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
        
    }
    
    
}
