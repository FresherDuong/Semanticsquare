package com.semanticsquare.thrillio.managers;

import com.semanticsquare.thrillio.DataStore;
import com.semanticsquare.thrillio.dao.BookmarkDao;
import com.semanticsquare.thrillio.entities.*;
import com.semanticsquare.thrillio.util.HttpConnect;
import com.semanticsquare.thrillio.util.IOUtil;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class BookmarkManager {
    private static BookmarkManager instance = new BookmarkManager();
    private static BookmarkDao dao = new BookmarkDao();

    private BookmarkManager(){}

    public static BookmarkManager getInstance(){
        return instance;
    }

    public Book createBook(long id, String title, /*String profileUrl,*/ int publicationYear,
                           String publisher, String[] authors, String genre, double amazonRating){
        Book book = new Book();

        book.setId(id);
        book.setTitle(title);
        //book.setProfileUrl(profileUrl);
        book.setPublicationYear(publicationYear);
        book.setPublisher(publisher);
        book.setAuthors(authors);
        book.setGenre(genre);
        book.setAmazonRating(amazonRating);
        return book;
    }

    public Movie createMovie(long id, String title, /*String profileUrl,*/ int releaseYear,
                             String[] cast, String[] directors, String genre, double imdbRating){
        Movie movie = new Movie();

        movie.setId(id);
        movie.setTitle(title);
        //movie.setProfileUrl(profileUrl);
        movie.setReleaseYear(releaseYear);
        movie.setCast(cast);
        movie.setDirectors(directors);
        movie.setGenre(genre);
        movie.setImdbRating(imdbRating);
        return movie;
    }

    public WebLink createWebLink(long id, String title, /*String profileUrl,*/ String url, String host){
        WebLink webLink = new WebLink();

        webLink.setId(id);
        webLink.setTitle(title);
        //webLink.setProfileUrl(profileUrl);
        webLink.setUrl(url);
        webLink.setHost(host);
        return webLink;
    }

    public Bookmark[][] getBookmark(){
        return dao.getBookmark();
    }

    public void saveUserBookmark(User user, Bookmark boomark){
        UserBookmark userBookmark = new UserBookmark();
        userBookmark.setUse(user);
        userBookmark.setBookmark(boomark);

        //In addition, save to file
        if (boomark instanceof WebLink) {
            try {
                String url = ((WebLink)boomark).getUrl();
                if (!url.endsWith(".pdf")) {
                    String webpageData = HttpConnect.download(((WebLink)boomark).getUrl());
                    if (webpageData != null) {
                        IOUtil.write(webpageData, boomark.getId());
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }


        //Actually save to DB
        dao.saveUserBookmark(userBookmark);
    }

    public void setKidFriendlyStatus(User user, String kidFriendlyStatus, Bookmark bookmark_items) {
        bookmark_items.setKidFriendlyStatus(kidFriendlyStatus);
        bookmark_items.setKidFriendlyMarkedBy(user);

        System.out.println("Kid friendly status: " + kidFriendlyStatus + " Marked by: " + user.getEmail() + " " + user.getUserType() + " " + bookmark_items);
    }

    public void share(User users, Bookmark bookmark_items) {
        bookmark_items.setSharedBy(users);

        System.out.println("Data to be shared: ");
        if(bookmark_items instanceof Book){
            System.out.println("By user: " + users.getEmail()+ " " + users.getUserType() + " "  + ((Book) bookmark_items).getXMLItemData()); //Just print, third-web isn't available
        }
        else if(bookmark_items instanceof WebLink){
            System.out.println("By user: " + users.getEmail()+ " " + users.getUserType() + " "  +((WebLink)bookmark_items).getXMLItemData());
        }

    }
}
