package com.semanticsquare.thrillio;

import com.semanticsquare.thrillio.constants.*;
import com.semanticsquare.thrillio.entities.*;
import com.semanticsquare.thrillio.managers.BookmarkManager;
import com.semanticsquare.thrillio.managers.UserManager;
import com.semanticsquare.thrillio.util.IOUtil;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    public static final int TOTAL_USER_COUNT = 5;
    public static final int USER_BOOKMARK_LIMIT = 5;
    public static final int BOOKMARK_TYPES_COUNT = 3;
    public static final int BOOKMARK_COUNT_PER_TYPE = 5;

    private static List<User> users = new ArrayList<>();
    private static List<List<Bookmark>> bookmarks = new ArrayList<>();
    private static List<UserBookmark> userBookmarks = new ArrayList<>();

    public static List<User> getUsers() {
        return users;
    }

    public static List<List<Bookmark>> getBookmarks(){
        return bookmarks;
    }

    public static void loadData(){
        loadUsers();
        loadWebLinks();
        loadMovies();
        loadBooks();
    }

    public static void loadUsers(){
        List<String> data = new ArrayList<>();

        IOUtil.read(data,"User.txt");

        for (String row : data) {
            String[] value = row.split("\t");

            Gender gender = Gender.MALE;
            if(value[5].equals("f")){
                gender = Gender.FEMALE;
            }else if(value[5].equals("t")){
                gender = Gender.TRANSGENDER;
            }

            User user = UserManager.getInstance().createUser(Long.parseLong(value[0]),value[1],value[2],value[3],value[4], gender, UserType.valueOf(value[6]));
            users.add(user);
        }
    }

    public static void loadWebLinks(){
        List<String> data = new ArrayList<>();
        IOUtil.read(data,"WebLink.txt");

        List<Bookmark> bookmarkList = new ArrayList<>();
        for (String row : data) {
            String[] value = row.split("\t");
            Bookmark bookmark = BookmarkManager.getInstance().createWebLink(Long.parseLong(value[0]),value[1],value[2],value[3]);
            bookmarkList.add(bookmark);
        }
        bookmarks.add(bookmarkList);
    }

    public static void loadMovies(){
        List<String> data = new ArrayList<>();
        IOUtil.read(data,"Movie.txt");

        List<Bookmark> bookmarkList = new ArrayList<>();
        for (String row : data) {
            String[] value = row.split("\t");
            String[] cast = value[3].split(",");
            String[] directors = value[4].split(",");
            Bookmark bookmark = BookmarkManager.getInstance().createMovie(Long.parseLong(value[0]),value[1],Integer.parseInt(value[2]), cast, directors, MovieGenre.valueOf(value[5]), Double.parseDouble(value[6]));
            bookmarkList.add(bookmark);
        }
        bookmarks.add(bookmarkList);
    }

    public static void loadBooks(){
        List<String> data = new ArrayList<>();
        IOUtil.read(data,"Book.txt");

        List<Bookmark> bookmarkList = new ArrayList<>();
        for (String row : data) {
            String[] value = row.split("\t");
            String[] authors = value[4].split(",");
            Bookmark bookmark = BookmarkManager.getInstance().createBook(Long.parseLong(value[0]),value[1],Integer.parseInt(value[2]),value[3], authors, BookGenre.valueOf(value[5]), Double.parseDouble(value[6]));
            bookmarkList.add(bookmark);
        }
        bookmarks.add(bookmarkList);
    }

    public static void add(UserBookmark userBookmark){
        userBookmarks.add(userBookmark);
    }
}
