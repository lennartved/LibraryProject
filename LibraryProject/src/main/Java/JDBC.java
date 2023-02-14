import org.jetbrains.annotations.NotNull;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import library.*;
public class JDBC {
    Scanner scanner = new Scanner(System.in);
    public JDBC(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException exception){
            exception.printStackTrace();
        }
    }
    public static Connection getConnection(){
        try{
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/videosAndBooks?useSSL=false",
                    "root",
                    "root");
            return con;
        }
        catch (SQLException a){
            a.printStackTrace();
        }
        return null;
    }
    public static ArrayList<Movie> getMovies(){
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        try(Connection con = getConnection()){
            Statement stmt = con.createStatement();
            String selectSql = "select * from director\n" +
                    "join movie m on director.id = m.directorId";
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while(resultSet.next()){
                String name = resultSet.getString("name");
                String title = resultSet.getString("title");
                Genre genre = Genre.valueOf(resultSet.getString("genre"));
                int minutes = resultSet.getInt("minutes");
                int movieId = resultSet.getInt("id");
                int directorId = resultSet.getInt("directorId");
                Movie tempMovie = new Movie();
                tempMovie.getDirector().setDirectorId(directorId);
                tempMovie.getDirector().setName(name);
                tempMovie.setTitle(title);
                tempMovie.setGenre(genre);
                tempMovie.setMinutes(minutes);
                tempMovie.setMovieId(movieId);
                movieArrayList.add(tempMovie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movieArrayList;
    }
    public ArrayList<Director> getDirectors(){
        ArrayList<Director> directorArrayList = new ArrayList<>();

        try(Connection con = getConnection()){
            Statement stmt = con.createStatement();
            String selectSql = "select * from director;";
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int bornDate = resultSet.getInt("bornDate");
                Director tempDirector = new Director() {
                };
                tempDirector.setDirectorId(id);
                tempDirector.setName(name);
                tempDirector.setBornDate(bornDate);
                directorArrayList.add(tempDirector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return directorArrayList;
    }
    public void addMovie(Movie newMovie){
        try(Connection con = getConnection()){
            Statement stmt = con.createStatement();

            String sql = "INSERT INTO movie(title, minutes, directorId, genre) " +
                    "VALUES ('" +
                    newMovie.getTitle()
                    + "', '" +
                    newMovie.getMinutes() +"', '" +
                    newMovie.getDirector().getDirectorId() + "', '" +
                    newMovie.getGenre()  + "')";

            stmt.execute(sql);
            System.out.println("Successfully added new movie!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void addBook(Book newBook){
        try(Connection con = getConnection()){
            Statement stmt = con.createStatement();

            String sql = "INSERT INTO book(title, pages, authorId, genre) " +
                    "VALUES ('" +
                    newBook.getTitle()
                    + "', '" +
                    newBook.getPages() +"', '" +
                    newBook.getAuthor().getAuthorId() + "', '" +
                    newBook.getGenre()  + "')";

            stmt.execute(sql);
            System.out.println("Successfully added new book!");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void addSerie(Series newSerie){
        String preparedSelectStmt = "insert into serie (title, numOfEpisodes,directorId,genre) values(?,?,?,?);";
        try(Connection con = getConnection()) {
            con.setAutoCommit(false);
            PreparedStatement prep = con.prepareStatement(preparedSelectStmt);
            prep.setString(1, newSerie.getTitle());
            prep.setInt(2, newSerie.getNumOfEpisodes());
            prep.setInt(3, newSerie.getDirector().getDirectorId());
            prep.setString(4, String.valueOf(newSerie.getGenre()));
            prep.executeUpdate();
            con.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Successfully added show: " + newSerie.getTitle());
        }

    }
    public int addDirector(Director newDirector){

        try(Connection con = getConnection()){
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO director(name, borndate)" +
                    "VALUES ('" +
                    newDirector.getName()
                    + "', '" +
                    newDirector.getBornDate() +"');";
            stmt.execute(sql);

            sql = "SELECT id FROM director where bornDate ="+newDirector.getBornDate();
            ResultSet resultSet = stmt.executeQuery(sql);
            int id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;
        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
    }
    public int addAuthor(Author newAuthor){
        try(Connection con = getConnection()){
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO author(name, borndate)" +
                    "VALUES ('" +
                    newAuthor.getName()
                    + "', '" +
                    newAuthor.getBornDate() +"');";
            stmt.execute(sql);
            sql = "SELECT id FROM author where bornDate ="+newAuthor.getBornDate();
            ResultSet resultSet = stmt.executeQuery(sql);
            int id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;
        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
    }

    public void updateSerie(Series newSerie){
        String preparedSelect = "update serie set title = ?, numOfEpisodes = ?, directorId = ?, genre = ? where id = ?;";
        try(Connection con = getConnection()){
            con.setAutoCommit(false);
            PreparedStatement prep = con.prepareStatement(preparedSelect);
            prep.setString(1, newSerie.getTitle());
            prep.setInt(2, newSerie.getNumOfEpisodes());
            prep.setInt(3, newSerie.getDirector().getDirectorId());
            prep.setString(4, String.valueOf(newSerie.getGenre()));
            prep.setInt(5, newSerie.getSerieId());
            prep.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Successfully updated show with id: " + newSerie.getSerieId());
        }
    }

    public void updateBook(Book newBook){
        String preparedSelect = "update book set title = ?, pages = ?, authorId = ?, genre = ? where id = ?;";
        try(Connection con = getConnection()){
            con.setAutoCommit(false);
            PreparedStatement prep = con.prepareStatement(preparedSelect);
            prep.setString(1, newBook.getTitle());
            prep.setInt(2, newBook.getPages());
            prep.setInt(3, newBook.getAuthor().getAuthorId());
            prep.setString(4, String.valueOf(newBook.getGenre()));
            prep.setInt(5, newBook.getBookId());
            prep.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Successfully updated book with id: " + newBook.getBookId());
        }
    }

    public void updateMovie(@NotNull Movie movieUpdate){
        String preparedSelect = "update movie set title = ?, minutes = ?, directorId = ?, genre = ? where id = ?;";
        try(Connection con = getConnection()){
            con.setAutoCommit(false);
            PreparedStatement prep = con.prepareStatement(preparedSelect);
            prep.setString(1, movieUpdate.getTitle());
            prep.setInt(2, movieUpdate.getMinutes());
            prep.setInt(3, movieUpdate.getDirector().getDirectorId());
            prep.setString(4, String.valueOf(movieUpdate.getGenre()));
            prep.setInt(5, movieUpdate.getMovieId());
            prep.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Succsessfully updated book with id: " + movieUpdate.getMovieId());
        }
    }
    public void deleteFromDatabase(String choise, int id){
        boolean sucsess = true;
        try(Connection con = getConnection()){
        String preparedSelect = "delete from "+choise+" where id = ?";

            con.setAutoCommit(false);
            PreparedStatement prep = con.prepareStatement(preparedSelect);
            prep.setInt(1, id);
            prep.executeUpdate();
            con.commit();

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Looks like this "+choise+" owns content in the database, please delete content first");
            sucsess = false;
        } catch (SQLException e) {
            System.out.println("SQLException");
            sucsess = false;
        }
        finally {
            if(sucsess){
            System.out.println("Succsessfully deleted "+choise+" with id: " + id);
            }
        }
    }
    public ArrayList<Series> getSeries(){
        ArrayList<Series> seriesArrayList = new ArrayList<Series>();

        try(Connection con = getConnection()){
            Statement stmt = con.createStatement();
            String sql = "select * from director\n" +
                    "join serie m on director.id = m.directorId";
            ResultSet resultSet = stmt.executeQuery(sql);
            while(resultSet.next()){
                int serieId = resultSet.getInt("id");
                int numOfEpisodes = resultSet.getInt("numOfEpisodes");
                int directorId = resultSet.getInt("directorId");
                String title = resultSet.getString("title");
                String genre = resultSet.getString("genre");
                String name = resultSet.getString("name");
                Series tempSerie = new Series();
                tempSerie.setSerieId(serieId);
                tempSerie.getDirector().setName(name);
                tempSerie.setNumOfEpisodes(numOfEpisodes);
                tempSerie.getDirector().setDirectorId(directorId);
                tempSerie.setTitle(title);
                tempSerie.setGenre(Genre.valueOf(genre));
                seriesArrayList.add(tempSerie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seriesArrayList;
    }
    public ArrayList<Book> getBooks(){
        ArrayList<Book> BookArrayList = new ArrayList<Book>();

        try(Connection con = getConnection()){
            Statement stmt = con.createStatement();
            String sql = "select * from author\n" +
                    "join Book m on author.id = m.authorId";
            ResultSet resultSet = stmt.executeQuery(sql);
            while(resultSet.next()){
                int bookId = resultSet.getInt("id");
                int pages = resultSet.getInt("pages");
                int authorId = resultSet.getInt("authorId");
                String title = resultSet.getString("title");
                String genre = resultSet.getString("genre");
                String name = resultSet.getString("name");
                Book tempBook = new Book();
                tempBook.setBookId(bookId);
                tempBook.getAuthor().setName(name);
                tempBook.setPages(pages);
                tempBook.getAuthor().setAuthorId(authorId);
                tempBook.setTitle(title);
                tempBook.setGenre(Genre.valueOf(genre));
                BookArrayList.add(tempBook);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BookArrayList;

    }
    public ArrayList<Author> getAuthors(){

        ArrayList<Author> authorArrayList = new ArrayList<>();
        try(Connection con = getConnection()){
            Statement stmt = con.createStatement();
            String selectSql = "select * from author;";
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int bornDate = resultSet.getInt("bornDate");
                Author tempAuthor = new Author() {
                };
                tempAuthor.setAuthorId(id);
                tempAuthor.setName(name);
                tempAuthor.setBornDate(bornDate);
                authorArrayList.add(tempAuthor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorArrayList;


    }
}
