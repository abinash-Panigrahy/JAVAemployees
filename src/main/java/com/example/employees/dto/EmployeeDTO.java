package com.example.employees.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmployeeDTO {
  private Long id;

  @NotBlank @Size(max=100)
  private String name;

  @NotBlank @Size(max=100)
  private String position;

  public EmployeeDTO() {}
  public EmployeeDTO(Long id, String name, String position) {
    this.id = id; this.name = name; this.position = position;
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getPosition() { return position; }
  public void setPosition(String position) { this.position = position; }
}
