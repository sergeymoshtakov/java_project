package com.company.models;

public interface ILibrary {
    public void addPublication(Publication publication);
    public void addPublication(Book book);
    public void addPublication(Almanac almanac);
    public void addPublication(Newspaper newspaper);
    public void initializeLibrary();
    public boolean removePublicationByName(String name);
}
