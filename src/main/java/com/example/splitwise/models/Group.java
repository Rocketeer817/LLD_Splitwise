package com.example.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity(name="groups")
public class Group extends BaseModel{
    private String name;
    private String description;
    @ManyToMany
    private List<User> members;
    @OneToMany(mappedBy = "group")
    private List<Expense> expenses;
    @OneToOne
    private User admin;

}
