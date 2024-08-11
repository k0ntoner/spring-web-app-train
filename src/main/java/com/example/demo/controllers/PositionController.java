package com.example.demo.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;



import com.example.demo.models.Position;

import com.example.demo.services.PositionService;



import org.springframework.web.bind.annotation.*;


@RestController
public class PositionController {
    @Autowired
    private PositionService positionService;
    @PostMapping("/positions")
    public ResponseEntity<Position> addPosition(@RequestBody Position position) {
        Position newPosition = positionService.addPosition(position);
        if(newPosition==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(position);
    }
    @GetMapping("/positions/{id}")
    public ResponseEntity<Position> getPositionById(@PathVariable Long id) {
        return positionService.getPositionById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
        
    }
    
    
}
