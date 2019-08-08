package com.semanticsquare.thrillio.constants;

public enum UserType {
    USER("user"),
    EDITOR("editor"),
    CHIEF_EDITOR("chief-editor");

    String type;
    private UserType(String type){
        this.type = type;
    }
}
