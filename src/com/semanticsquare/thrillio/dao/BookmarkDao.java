package com.semanticsquare.thrillio.dao;

import com.semanticsquare.thrillio.DataStore;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.UserBookmark;
import com.semanticsquare.thrillio.entities.WebLink;

import java.util.ArrayList;
import java.util.List;

public class BookmarkDao {
    public List<List<Bookmark>> getBookmark(){
        return DataStore.getBookmarks();
    }

    public void saveUserBookmark(UserBookmark userBookmark){
        //Because we don't have SQL Queries, so we do it instead
        DataStore.add(userBookmark);
    }

    // In real applications, we would have SQL or Hibernate queries.
    public List<WebLink> getAllWebLinks(){
        List<WebLink> result =  new ArrayList<>();

        List<List<Bookmark>> bookmarks = DataStore.getBookmarks();
        List<Bookmark> allWebLinks = bookmarks.get(0);

        for(Bookmark items : allWebLinks){
            result.add((WebLink)items);
        }

        return result;
    }

    public List<WebLink> getWebLinks(WebLink.DownloadStatus downloadStatus){
        List<WebLink> result =  new ArrayList<>();

        List<WebLink> allWebLinks = getAllWebLinks();
        for(WebLink webLink : allWebLinks){
            if(webLink.getDownloadStatus().equals(downloadStatus)){
                result.add(webLink);
            }
        }

        return result;
    }
}
