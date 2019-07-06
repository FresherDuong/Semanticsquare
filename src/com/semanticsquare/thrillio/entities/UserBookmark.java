package com.semanticsquare.thrillio.entities;

public class UserBookmark {
    private User user;
    private Bookmark bookmark;

    public User getUse() {
        return user;
    }

    public void setUse(User use) {
        this.user = use;
    }

    public Bookmark getBookmark() {
        return bookmark;
    }

    public void setBookmark(Bookmark bookmark) {
        this.bookmark = bookmark;
    }
}
