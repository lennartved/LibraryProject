package library;

public abstract class Media {
    private String title;
    private Genre genre;
    public String getTitle() {
        return title;
    }
    public Genre getGenre() {
        return genre;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }


    public String printOut() {


        return "\nTitle: " + this.getTitle()
                + "\nGenre: " + this.getGenre()
                +"\n";
    }
}