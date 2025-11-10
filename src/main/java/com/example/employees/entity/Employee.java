package com.example.employees.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable=false, length=100)
  private String name;
  @Column(nullable=false, length=100)
  private String position;

  public Employee() {}
  public Employee(Long id, String name, String position) {
    this.id = id; this.name = name; this.position = position;
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getPosition() { return position; }
  public void setPosition(String position) { this.position = position; }
}
