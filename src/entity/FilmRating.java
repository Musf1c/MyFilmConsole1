package entity;

import java.io.Serializable;

public class FilmRating implements Serializable {
    protected int idFilm;
    protected String title;
    protected String userLogin;
    protected int rating;


    public FilmRating(int idFilm, String title, String userLogin, int rating) {
        this.idFilm = idFilm;
        this.title = title;
        this.userLogin = userLogin;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public String toString() {
        return "Рейтинг фильма с " +
                "ID: " + idFilm +
                " Названием: " + title +
                ", равен: " + rating;
    }
}
