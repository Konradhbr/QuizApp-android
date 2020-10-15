package com.example.quiz;

public class Player {

    private String name;
    private String result;

    public Player(String name, String result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
            return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Player() {}

    public String toString() {
        return this.name + " - " + " punkty " + this.result;
    }

}

