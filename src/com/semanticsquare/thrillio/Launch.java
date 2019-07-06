package com.semanticsquare.thrillio;

import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.managers.BookmarkManager;
import com.semanticsquare.thrillio.managers.UserManager;

public class Launch {
    private static User[] users;
    private static Bookmark[][] bookmarks;

    private static void printUserData(){
        for (User items: users) {
            System.out.println(items);
        }
    }

    private static void printBookmarkData(){
        for (Bookmark[] items1DArr: bookmarks) {
            for (Bookmark items: items1DArr) {
                System.out.println(items);
            }
        }
    }

    private static void loadData(){
        System.out.println("**Loading data ...");
        DataStore.loadData();

        users = UserManager.getInstance().getUser();
        bookmarks = BookmarkManager.getInstance().getBookmark();

        System.out.println("Printing data ...");
        printUserData();
        System.out.println("Continuing ...");
        printBookmarkData();
    }

    public static void startBookmarking(){
        System.out.println("\n**Bookmarking ...");
        for (User items: users) {
            View.bookmark(items,bookmarks);
        }
    }

    public static void main(String[] args) {
        loadData();
        startBookmarking();
    }
}
