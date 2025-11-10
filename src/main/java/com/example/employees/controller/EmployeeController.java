package com.example.employees.controller;

import com.example.employees.dto.EmployeeDTO;
import com.example.employees.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Validated
public class EmployeeController {

  private final EmployeeService service;

  @Autowired
  public EmployeeController(EmployeeService service) {
    this.service = service;
  }

  @PostMapping("/bulk")
  public ResponseEntity<List<EmployeeDTO>> createBulk(@RequestBody List<@Valid EmployeeDTO> payload) {
    return new ResponseEntity<>(service.createBulk(payload), HttpStatus.CREATED);
  }

  @GetMapping
  public List<EmployeeDTO> list() { return service.findAll(); }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteOne(@PathVariable Long id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteAll() {
    service.deleteAll();
    return ResponseEntity.noContent().build();
  }
}
