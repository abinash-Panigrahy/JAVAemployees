package com.example.employees.service;

import com.example.employees.dto.EmployeeDTO;
import java.util.List;

public interface EmployeeService {
  List<EmployeeDTO> createBulk(List<EmployeeDTO> list);
  List<EmployeeDTO> findAll();
  void deleteById(Long id);
  void deleteAll();
}
