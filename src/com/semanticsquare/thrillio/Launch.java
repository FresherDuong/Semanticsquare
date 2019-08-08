package com.semanticsquare.thrillio;

import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;
import com.semanticsquare.thrillio.managers.BookmarkManager;
import com.semanticsquare.thrillio.managers.UserManager;

import java.util.List;

public class Launch {
    private static List<User> users;
    private static List<List<Bookmark>> bookmarks;

    private static void printUserData(){
        for (User items: users) {
            System.out.println(items);
        }
    }

    private static void printBookmarkData(){
        for (List<Bookmark> items1DArr: bookmarks) {
            for (Bookmark items: items1DArr) {
                System.out.println(items);
            }
        }
    }

    private static void loadData(){
//        System.out.println("**Loading data ...");
        DataStore.loadData();

        users = UserManager.getInstance().getUser();
        bookmarks = BookmarkManager.getInstance().getBookmark();

//        System.out.println("Printing data ...");
//        printUserData();
//        System.out.println("Continuing ...");
//        printBookmarkData();
    }

    public static void startBookmarking(){
        System.out.println("\n**Bookmarking ...");
        for (User user: users) {
            View.bookmark(user,bookmarks);
        }
    }

    public static void startBrowsing(){
        System.out.println("\n**Browsing ...");
        for (User items: users) {
            View.browse(items,bookmarks);
        }
    }

    public static void main(String[] args) {
        loadData();
        //startBookmarking();
        startBrowsing();
    }
}
