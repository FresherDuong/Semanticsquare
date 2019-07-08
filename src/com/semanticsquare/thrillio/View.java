package com.semanticsquare.thrillio;

import com.semanticsquare.thrillio.constants.KidFriendlyStatus;
import com.semanticsquare.thrillio.constants.UserType;
import com.semanticsquare.thrillio.controllers.BookmarkController;
import com.semanticsquare.thrillio.entities.Bookmark;
import com.semanticsquare.thrillio.entities.User;

public class View {

    //User bookmarking from View UI
    public static void bookmark(User users, Bookmark[][] bookmarks){
        System.out.println("\nUser " + users.getEmail() + " is bookmarking ...");
        for(int i = 0; i < DataStore.USER_BOOKMARK_LIMIT; i++){
            int typeOfset = (int)(Math.random() * DataStore.BOOKMARK_TYPES_COUNT);
            int bookmarkOfset = (int)(Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE);

            Bookmark boomark = bookmarks[typeOfset][bookmarkOfset];
            //Save to DB back-end
            BookmarkController.getInstance().saveUserBookmark(users, boomark);

            System.out.println(boomark);
        }
    }


    //
    public static void browse(User users, Bookmark[][] bookmarks){
        System.out.println("\nUser " + users.getEmail() + " is browsing items ...");
        int bookmarkCount = 0;

        for(Bookmark[] bookmarks1 : bookmarks){
            for(Bookmark bookmark_items : bookmarks1){
                //Bookmarking
                if(bookmarkCount < DataStore.USER_BOOKMARK_LIMIT){ // Check for limitation
                    boolean isDesiredBookmarkDecision = getBookmarkDecision();
                    if(isDesiredBookmarkDecision){
                        bookmarkCount++;
                        //Save to DB back-end
                        BookmarkController.getInstance().saveUserBookmark(users, bookmark_items);

                        System.out.println("New items bookmarked: " + bookmark_items);
                    }
                }

                if(users.getUserType().equals(UserType.EDITOR)
                        || users.getUserType().equals(UserType.CHIEF_EDITOR)){
                    if(bookmark_items.isKidFriendlyEligible()
                            && bookmark_items.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)){
                        String kidFriendlyStatus =  getKidFriendlyStatusDecision();
                        if(!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)){
                            bookmark_items.setKidFriendlyStatus(kidFriendlyStatus);

                            System.out.println("Kid friendly status: " + kidFriendlyStatus + " " + bookmark_items);
                        }
                    }
                }
            }
        }
    }

    private static String getKidFriendlyStatusDecision() {
        return Math.random() < 0.4 ? KidFriendlyStatus.APPROVED :
                (Math.random() >= 0.4 && Math.random() < 0.8 ? KidFriendlyStatus.REJECTED : KidFriendlyStatus.UNKNOWN);
    }


    //Just for random decision whether bookmarking or not
    private static boolean getBookmarkDecision() {
        return Math.random() > 0.5 ? true : false;
    }
}
