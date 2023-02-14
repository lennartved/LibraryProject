package library;

public class Book extends Media {

    private Author author = new Author();
    private int bookId;
    private int pages;

    public void setPages(int pages) {this.pages = pages;}
    public int getPages() {return pages;}
    public Author getAuthor(){return author;}
    public int getBookId(){return bookId;}
    public void setAuthor(Author author){this.author = author;}
    public void setBookId(int bookId) {this.bookId = bookId;}
    public String printOut(){
        return "\nAuthor: "+this.getAuthor().getName()
                + "\nTitle: " + this.getTitle()
                + "\nGenre: " + this.getGenre()
                + "\nPages: " + this.getPages()
                + "\nBookId: " + this.getBookId()
                +"\n";
    }
}
