package com.semanticsquare.thrillio.managers;

import com.semanticsquare.thrillio.dao.UserDao;
import com.semanticsquare.thrillio.entities.User;

public class UserManager { //JVM loads class to mem and also initializes fields
    private static UserManager instance = new UserManager();
    private static UserDao dao = new UserDao(); // Singleton is stateless - static

    private UserManager() {
    }

    public static UserManager getInstance() {
        return instance;
    }

    public User createUser(long id, String email, String password, String firstName,
                           String lastName, int gender, String userType) {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setGender(gender);
        user.setUserType(userType);
        return user;
    }

    public User[] getUser(){
        return dao.getUser();
    }

}

/*
* MVC - View <--> Control <--> Model(Manager <--> Dao) <--> Database
* */