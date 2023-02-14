package library;

public class Movie extends Media {
    private Director director = new Director();
    private int movieId;
    private int minutes;
    public Director getDirector() {return director;}
    public void setDirector(Director director) {this.director = director;}
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
    public int getMovieId() {
        return movieId;
    }
    public int getMinutes() {
        return minutes;
    }

    public String printOut(){
        return "\nDirector: "+this.getDirector().getName()
                + "\nTitle: " + this.getTitle()
                + "\nGenre: " + this.getGenre()
                + "\nMinutes: " + this.getMinutes()
                + "\nMovieId: " + this.getMovieId()
                +"\n";
    }
}