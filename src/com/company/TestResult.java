package com.company;

public class TestResult {
    private boolean success;
    private String comment;

    public TestResult(boolean success, String comment) {
        this.success = success;
        this.comment = comment;
    }

    public boolean success() {
        return success;
    }

    public String getComment() {
        return comment;
    }
}
