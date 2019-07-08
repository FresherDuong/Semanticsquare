package com.semanticsquare.thrillio;

import com.semanticsquare.thrillio.controllers.BookmarkController;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;

public class View {

    public static void bookmark(User users, Bookmark[][] bookmarks){
        System.out.println("User " + users.getEmail() + " is bookmarking");
        for(int i = 0; i < DataStore.USER_BOOKMARK_LIMIT; i++){
            int typeOfset = (int)(Math.random() * DataStore.BOOKMARK_TYPES_COUNT);
            int bookmarkOfset = (int)(Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE);

            Bookmark boomark = bookmarks[typeOfset][bookmarkOfset];

            BookmarkController.getInstance().saveUserBookmark(users, boomark);

            System.out.println(boomark);
        }
    }
}
