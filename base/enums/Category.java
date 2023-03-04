package ru.gb.lesson_5.base.enums;

public enum Category {
    FOOD(1, "Food");

    public final int id;
    public final String title;
    Category(int id, String title) {
        this.id = id;
        this.title = title;
    }
}
