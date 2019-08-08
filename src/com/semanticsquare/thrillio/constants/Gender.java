package com.semanticsquare.thrillio.constants;

public enum Gender {
    MALE(0),
    FEMALE(1),
    TRANSGENDER(2);

    int gender;
    private Gender(int gender){
        this.gender = gender;
    }
}
