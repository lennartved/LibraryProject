package library;

public class Author {
    int AuthorId;
    String name;
    int bornDate;

    public int getAuthorId() {
        return AuthorId;
    }

    public String getName() {
        return name;
    }

    public int getBornDate() {
        return bornDate;
    }

    public void setAuthorId(int authorId) {
        AuthorId = authorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBornDate(int bornDate) {
        this.bornDate = bornDate;
    }
}
