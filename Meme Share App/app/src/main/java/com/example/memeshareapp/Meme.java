package com.example.memeshareapp;

public class Meme {
    String url;
    String author;
    String caption;
    String postLink;

    public Meme(String author, String caption, String url, String postLink) {
        this.author = author;
        this.caption = caption;
        this.url = url;
        this.postLink = postLink;
    }

    public String getPostLink() {
        return postLink;
    }

    public void setPostLink(String postLink) {
        this.postLink = postLink;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
