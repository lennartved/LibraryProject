package library;

public class Series extends Media {

    private Director director = new Director();
    private int serieId;
    private int numOfEpisodes;
    public Director getDirector() {return director;}
    public void setSerieId(int serieId) {
        this.serieId = serieId;
    }
    public void setNumOfEpisodes(int numOfEpisodes) {
        this.numOfEpisodes = numOfEpisodes;
    }
    public int getSerieId() {
        return serieId;
    }
    public int getNumOfEpisodes() {
        return numOfEpisodes;
    }

    public String printOut(){
        return "\nDirector: "+this.getDirector().getName()
                + "\nTitle: " + this.getTitle()
                + "\nGenre: " + this.getGenre()
                + "\nNumOfEpisodes: " + this.getNumOfEpisodes()
                + "\nSerieId: " + this.getSerieId()
                +"\n";
    }
}
