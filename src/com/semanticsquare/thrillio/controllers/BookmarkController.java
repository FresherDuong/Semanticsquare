package com.semanticsquare.thrillio.controllers;

import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.managers.BookmarkManager;

public class BookmarkController {
    private static BookmarkController instance = new BookmarkController();

    private BookmarkController(){}

    public static BookmarkController getInstance(){
        return instance;
    }

    public void saveUserBookmark(User users, Bookmark boomark){
        BookmarkManager.getInstance().saveUserBookmark(users, boomark);
    }
}
