package com.semanticsquare.thrillio.dao;

import com.semanticsquare.thrillio.DataStore;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.UserBookmark;

import java.util.List;

public class BookmarkDao {
    public List<List<Bookmark>> getBookmark(){
        return DataStore.getBookmarks();
    }

    public void saveUserBookmark(UserBookmark userBookmark){
        //Because we don't have SQL Queries, so we do it instead
        DataStore.add(userBookmark);
    }
}
