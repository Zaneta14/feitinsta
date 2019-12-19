package com.example.feitinsta;

public class Comment {
    private final User user;
    private final String comment;

    Comment(User user, String comment) {
        this.user=user;
        this.comment=comment;
    }

    User getUser() { return user; }
    String getCommentt() { return comment; }
}

