package com.epam.library.entity;

public class InterfaceLang {
    private int id;
    private String name;
    private String locale;

    public InterfaceLang(int id, String name, String locale) {
        this.id = id;
        this.name = name;
        this.locale = locale;
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

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public String toString() {
        return "InterfaceLang{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", locale='" + locale + '\'' +
                '}';
    }
}
