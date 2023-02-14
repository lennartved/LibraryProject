package library;

public class Director {
    private int directorId;
    private String name;
    private int bornDate; //must be an int 8
    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setBornDate(int bornDate) {
        this.bornDate = bornDate;
    }
    public int getDirectorId() {
        return directorId;
    }
    public String getName() {return name;}
    public int getBornDate() {
        return bornDate;
    }
    public String printOutDirector(){
        return
                "directorId: " + this.directorId + "\n" +
                "name: " + this.name + "\n" +
                "born: " + this.bornDate + "\n"
                ;
    }
}