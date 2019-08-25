package com.hlebon.betpawa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private Integer id;

    private String name;
}
