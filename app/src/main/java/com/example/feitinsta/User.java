package com.example.feitinsta;

class User {
    private final String username;
    private final int imageRes;

    User(String username, int imageRes) {
        this.username=username;
        this.imageRes=imageRes;
    }

    String getUsername() { return username; }
    int getImageRes() { return imageRes; }
}
