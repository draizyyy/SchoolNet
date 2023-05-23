package com.draizyyy.demo.model;

import org.apache.juli.logging.Log;

public class User {
    private Long id;
    private String name;
    private String email;
    private Pet pet;

    public static class Pet {
        private String name;
        private String type;
        private int age;

        public Pet(String name, String type, int age) {
            this.name = name;
            this.type = type;
            this.age = age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public int getAge() {
            return age;
        }

    }
    public User(Long id, String name, String email, Pet pet) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pet = pet;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Pet getPet() {
        return pet;
    }

}
