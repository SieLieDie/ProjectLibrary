package com.epam.library.entity;

public class Book {
    private int id;
    private String name;
    private String author;
    private String genre;
    private int inStoke;
    private String description;
    private boolean isFavorite;

    public Book(int id, String name, String author, String genre, int inStoke, String description, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.inStoke = inStoke;
        this.description = description;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getInStoke() {
        return inStoke;
    }

    public void setInStoke(int inStoke) {
        this.inStoke = inStoke;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", inStoke=" + inStoke +
                ", isFavorite=" + isFavorite +
                '}';
    }
}
