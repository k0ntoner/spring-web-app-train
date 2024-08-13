package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.models.Position;
import com.example.demo.repositories.PositionRepository;

@Service
public class PositionService {
    @Autowired
    private PositionRepository positionRepository;
    public Position addPosition(Position position){
        try {
            return positionRepository.save(position);
        } catch (Exception e) {
            return null;
        }
        
    }
    public List<Position> getAllPositions(){
        return positionRepository.findAll();
    }
    public void deletePositionById(Long id){
        try {
            positionRepository.deleteById(id);
        } catch (Exception e) {
            
        }
    }
    public Optional<Position> getPositionById(Long id){
        try {
            return positionRepository.findById(id);
        } catch (Exception e) {
            return null;
        }
        
    }
}
