package com.example.employees.service.impl;

import com.example.employees.dto.EmployeeDTO;
import com.example.employees.entity.Employee;
import com.example.employees.repository.EmployeeRepository;
import com.example.employees.service.EmployeeService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository repository;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository repository) {
    this.repository = repository;
  }

  private EmployeeDTO toDTO(Employee e) {
    return new EmployeeDTO(e.getId(), e.getName(), e.getPosition());
  }

  private Employee toEntity(EmployeeDTO d) {
    Long id = d.getId();
    String name = d.getName() == null ? "" : d.getName().trim();
    String pos  = d.getPosition() == null ? "" : d.getPosition().trim();
    return new Employee(id, name, pos);
  }

  @Override
  public List<EmployeeDTO> createBulk(List<EmployeeDTO> list) {
    if (list == null || list.size() != 5) {
      throw new ValidationException("Exactly 5 employees are required");
    }
    List<Employee> saved = repository.saveAll(
      list.stream().map(this::toEntity).collect(Collectors.toList())
    );
    return saved.stream()
      .map(this::toDTO)
      .sorted(Comparator.comparing(EmployeeDTO::getId, Comparator.nullsLast(Comparator.naturalOrder())).reversed())
      .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public List<EmployeeDTO> findAll() {
    return repository.findAll().stream()
      .map(this::toDTO)
      .sorted((a,b) -> a.getName().compareToIgnoreCase(b.getName()))
      .collect(Collectors.toList());
  }

  @Override public void deleteById(Long id) { repository.deleteById(id); }
  @Override public void deleteAll() { repository.deleteAll(); }
}
