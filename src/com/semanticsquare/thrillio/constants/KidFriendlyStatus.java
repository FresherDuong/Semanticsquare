package com.semanticsquare.thrillio.constants;

public enum KidFriendlyStatus {
    APPROVED("approved"),
    REJECTED("rejected"),
    UNKNOWN("unknown");

    String status;
    private KidFriendlyStatus(String status){
        this.status = status;
    }
}
