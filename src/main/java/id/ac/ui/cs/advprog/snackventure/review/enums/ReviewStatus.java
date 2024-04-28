package id.ac.ui.cs.advprog.snackventure.review.enums;

import lombok.Getter;

@Getter
public enum ReviewStatus{
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED");

    private final String value;

    private ReviewStatus(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (ReviewStatus reviewStatus : ReviewStatus.values()) {
            if (reviewStatus.name().equals(param)) {
                return true;
            }
        }
        return false;
    }
}
