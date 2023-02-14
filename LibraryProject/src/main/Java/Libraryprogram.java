import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Scanner;

import library.*;


public class Libraryprogram {

    JDBC obj = new JDBC();
    Movie movie = new Movie();
    Director director = new Director();
    Scanner scanner = new Scanner(System.in);

    public void showAllByCat(String category){
        if(category == "movie"){
            ArrayList<Movie> allMovies = obj.getMovies();
            for (Movie i : allMovies) {
                System.out.println(i.printOut());
            }
        }else if(category == "series"){
            ArrayList<Series> allSeries = obj.getSeries();
            for (Series i : allSeries) {
                System.out.println(i.printOut());
            }
        }
        else if(category == "book"){
            ArrayList<Book> allBooks = obj.getBooks();
            for (Book i : allBooks) {
                System.out.println(i.printOut());
            }
        }else{
            System.out.println("Illegal parameter");
        }
    }
    public void showAllDirectors() {
        ArrayList<Director> allDirectors = obj.getDirectors();
        for (Director i : allDirectors) {
            System.out.println(i.printOutDirector());
        }
    }

    public void createMovie() {
        try {
            ArrayList<Director> allDirectors = obj.getDirectors();
            System.out.println("Title: ");
            String title = scanner.nextLine();
            Genre genre = addGenre();
            System.out.println("Minutes");
            int minutes = scanner.nextInt();
            System.out.println("Director Id: ");
            int directorIdInput = scanner.nextInt();
            scanner.nextLine();
            Movie newMovie = new Movie();
            newMovie.setTitle(title);
            newMovie.setMinutes(minutes);
            directorIdInput = checkDirectorExist(directorIdInput);
            newMovie.getDirector().setDirectorId(directorIdInput);
            newMovie.setGenre(genre);
            obj.addMovie(newMovie);
        } catch (IllegalArgumentException e) {

        }
    }
    public Genre addGenre(){
        ArrayList<Genre> genreList = new ArrayList<>(EnumSet.allOf(Genre.class));
        System.out.println("Genre: ");
        String genre = scanner.nextLine().toUpperCase();
        boolean genreExist = false;
        while(genreExist==false){
            for(Genre i: genreList){
                if(Objects.equals(String.valueOf(i), genre)){
                    genreExist = true;
                }
            }
            if(genreExist == false){
                System.out.println("Looks like genre doesnt exist! Enter genre again: ");
                genre = scanner.nextLine().toUpperCase();
            }
        }
        return Genre.valueOf(genre);
    }

    public void createSerie(){
        try {
            ArrayList<Director> allDirectors = obj.getDirectors();
            System.out.println("Title: ");
            String title = scanner.nextLine();
            System.out.println("Genre: ");
            Genre genre = addGenre();
            System.out.println("Number of Episodes:");
            int numOfEpisodes = scanner.nextInt();
            System.out.println("Director Id: ");
            int directorIdInput = scanner.nextInt();
            scanner.nextLine();
            Series newSerie = new Series();
            newSerie.setTitle(title);
            newSerie.setGenre(genre);
            newSerie.setNumOfEpisodes(numOfEpisodes);
            directorIdInput = checkDirectorExist(directorIdInput);
            newSerie.getDirector().setDirectorId(directorIdInput);
            obj.addSerie(newSerie);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid genre");
        }
    }

    public int createDirector() {
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Borndate: ");
        int bornDate = scanner.nextInt();
        scanner.nextLine();
        Director newDirector = new Director();
        newDirector.setBornDate(bornDate);
        newDirector.setName(name);
        int i = obj.addDirector(newDirector);
        return i;
    }
    public int createAuthor(){
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Borndate: ");
        int bornDate = scanner.nextInt();
        scanner.nextLine();
        Author newAuthor = new Author();
        newAuthor.setBornDate(bornDate);
        newAuthor.setName(name);
        int i = obj.addAuthor(newAuthor);
        return i;
    }
    public void showMovieByGenre(){
        ArrayList<Movie> allMovies = obj.getMovies();
        boolean notValidGenre = true;
            while(notValidGenre) {
                try{
                    System.out.println("\nType in genre: ");
                    String genreInput = scanner.nextLine().toUpperCase();
                    for (Movie i : allMovies) {
                        if (i.getGenre().equals(Genre.valueOf(genreInput))) {
                            System.out.println(i.printOut());
                        }
                    }
                    notValidGenre = false;
                }
                catch (IllegalArgumentException e){
                    System.out.println("Not a valid genre, try again with these options: ");

                    ArrayList<Genre> genreList = new ArrayList<>(EnumSet.allOf(Genre.class));
                    for(Genre i: genreList){
                        System.out.print(i+", ");
                    }
                }
        }
    }
    public void showSerieByGenre(){
        ArrayList<Series> allSeries = obj.getSeries();
        boolean notValidGenre = true;
        while(notValidGenre) {
            try{
                System.out.println("\nType in genre: ");
                String genreInput = scanner.nextLine().toUpperCase();
                for (Series i : allSeries) {
                    if (i.getGenre().equals(Genre.valueOf(genreInput))) {
                        System.out.println(i.printOut());
                    }
                }
                notValidGenre = false;
            }
            catch (IllegalArgumentException e){
                System.out.println("Not a valid genre, try again with these options: ");

                ArrayList<Genre> genreList = new ArrayList<>(EnumSet.allOf(Genre.class));
                for(Genre i: genreList){
                    System.out.print(i+", ");
                    }
                }
            }
        }
    public void showBookByGenre(){
        ArrayList<Book> allBooks = obj.getBooks();
        boolean notValidGenre = true;
        while(notValidGenre) {
            try{
                System.out.println("\nType in genre: ");
                String genreInput = scanner.nextLine().toUpperCase();
                for (Book i : allBooks) {
                    if (i.getGenre().equals(Genre.valueOf(genreInput))) {
                        System.out.println(i.printOut());
                    }
                }
                notValidGenre = false;
            }
            catch (IllegalArgumentException e){
                System.out.println("Not a valid genre, try again with these options: ");

                ArrayList<Genre> genreList = new ArrayList<>(EnumSet.allOf(Genre.class));
                for(Genre i: genreList){
                    System.out.print(i+", ");
                }
            }
        }



    }
    public void modifyMovie(){
        boolean notExist = true;
        int movieId = -1;
        while(notExist) {
            movieId = -1;
            try {
                ArrayList<Movie> allMovies = obj.getMovies();
                System.out.println("Which movie do you want to modify? Type in movie ID:");
                movieId = scanner.nextInt();
                scanner.nextLine();
                for (Movie i : allMovies) {
                    if (movieId == i.getMovieId()) {
                        notExist = false;
                    }
                }
                if (notExist) {
                    System.out.println("Movie does not exist, try again");
                }
            }catch(java.util.InputMismatchException e){
                System.out.println("Not a valid input");
            }
       }
        System.out.println("You have chosen to update movie, with ID: "+movieId);
        System.out.println("New title: ");
        String title = scanner.nextLine();
        System.out.println("New minutes: ");
        int minutes = scanner.nextInt();
        scanner.nextLine();
        System.out.print("New ");
        Genre genre = addGenre();
        System.out.println("New director ID: ");
        int directorIdInput = scanner.nextInt();
        scanner.nextLine();
        Movie movieUpdate = new Movie();
        movieUpdate.setTitle(title);
        movieUpdate.setMinutes(minutes);
        try {
            movieUpdate.setGenre(genre);
        }catch(IllegalArgumentException e) {

        }
        movieUpdate.setMovieId(movieId);
        directorIdInput = checkDirectorExist(directorIdInput);
        movieUpdate.getDirector().setDirectorId(directorIdInput);
        obj.updateMovie(movieUpdate);
    }

    public int checkDirectorExist(int directorIdInput){
        ArrayList<Director> allDirectors = obj.getDirectors();
        boolean exist = false;
        for (Director i : allDirectors) {
            if (directorIdInput == i.getDirectorId()) {
                exist = true;
            }
        }
        while (exist == false) {

            System.out.println("Seems like this director does not exist! \n1 = Add new director\n2 = Try a different id");
            Scanner scanner2 = new Scanner(System.in);
            String option = scanner2.nextLine();
            switch (option) {
                case "1":
                    directorIdInput = createDirector();
                    exist = true;
                    break;
                case "2":
                    System.out.println("Director Id:");
                    directorIdInput = scanner.nextInt();
                    scanner.nextLine();
                    for (Director i : allDirectors) {
                        if (directorIdInput == i.getDirectorId()) {
                            exist = true;
                        }
                    }
                    break;
            }
        }
        return directorIdInput;
    }
    public void modifySerie(){
        ArrayList<Director> allDirectors = obj.getDirectors();
        boolean notExist = true;
        int serieId = -1;
        while(notExist) {
            serieId = -1;
            try {
                ArrayList<Series> allSeries = obj.getSeries();
                System.out.println("Which show do you want to modify? Type in serie ID:");
                serieId = scanner.nextInt();
                scanner.nextLine();
                for (Series i : allSeries) {
                    if (serieId == i.getSerieId()) {
                        notExist = false;
                    }
                }
                if (notExist) {
                    System.out.println("Show does not exist, try again");
                }
            }catch(java.util.InputMismatchException e){
                System.out.println("Not a valid input");
            }
        }
        System.out.println("You have chosen to update the show with ID: "+serieId);
        System.out.println("New title: ");
        String title = scanner.nextLine();
        System.out.println("New number of episodes: ");
        int numOfEpisodes = scanner.nextInt();
        scanner.nextLine();
        System.out.print("New ");
        Genre genre = addGenre();
        System.out.println("New director ID: ");
        int directorIdInput = scanner.nextInt();
        scanner.nextLine();
        Series serieUpdate = new Series();
        serieUpdate.setTitle(title);
        serieUpdate.setNumOfEpisodes(numOfEpisodes);
        try {
            serieUpdate.setGenre(genre);
        }catch(IllegalArgumentException e) {
            System.out.println("Genre doesn't exist, please pick a genre that exist, start over");
            return;
        }
        serieUpdate.setSerieId(serieId);
        directorIdInput = checkDirectorExist(directorIdInput);
        serieUpdate.getDirector().setDirectorId(directorIdInput);
        obj.updateSerie(serieUpdate);
    }
    public void modifyBook(){
        ArrayList<Author> allAuthors = obj.getAuthors();
        boolean notExist = true;
        int bookId = -1;
        while(notExist) {
            bookId = -1;
            try {
                ArrayList<Book> allBooks = obj.getBooks();
                System.out.println("Which book do you want to modify? Type in book ID:");
                bookId = scanner.nextInt();
                scanner.nextLine();
                for (Book i : allBooks) {
                    if (bookId == i.getBookId()) {
                        notExist = false;
                    }
                }
                if (notExist) {
                    System.out.println("Book does not exist, try again");
                }
            }catch(java.util.InputMismatchException e){
                System.out.println("Not a valid input");
            }
        }
        System.out.println("You have chosen to update the book with ID: "+ bookId);
        System.out.println("New title: ");
        String title = scanner.nextLine();
        System.out.println("New number of pages: ");
        int numOfPages = scanner.nextInt();
        scanner.nextLine();
        System.out.print("New ");
        Genre genre = addGenre();
        System.out.println("New author ID: ");
        int authorIdInput = scanner.nextInt();
        scanner.nextLine();
        Book bookUpdate = new Book();
        bookUpdate.setTitle(title);
        bookUpdate.setPages(numOfPages);
        try {
            bookUpdate.setGenre(genre);
        }catch(IllegalArgumentException e) {
            System.out.println("Genre doesn't exist, please pick a genre that exist, start over");
            return;
        }
        bookUpdate.setBookId(bookId);
        authorIdInput = checkAuthorExist(authorIdInput);
        bookUpdate.getAuthor().setAuthorId(authorIdInput);
        obj.updateBook(bookUpdate);

    }
    public void delete(){
        System.out.println("What do you wan't do delete: \n1 = Movie\n2 = Serie\n3 = Book\n4 = Director\n5 = Author");
        String choise = null;
        boolean letsContinue = false;
        while(letsContinue == false) {
            choise = scanner.nextLine();
            if (choise.equals("1")) {
                choise = "movie";
                letsContinue = true;
            } else if (choise.equals("2")) {
                choise = "serie";
                letsContinue = true;
            } else if (choise.equals("3")) {
                choise = "book";
                letsContinue = true;
            } else if (choise.equals("4")) {
                choise = "director";
                letsContinue = true;
            }
            else if (choise.equals("5")
            ) {
                choise = "author";
                letsContinue = true;
            } else{
                System.out.println("Not an option, try again:");
            }
        }
        System.out.println("Enter ID of the "+ choise+" you wan't to remove");
        int id = scanner.nextInt();
        scanner.nextLine();
            obj.deleteFromDatabase(choise, id);

    }
    public void keywordSearch(){
        System.out.println("Enter in keyword: ");
        String input = scanner.nextLine().toUpperCase();
        ArrayList<Media> allMedia = new ArrayList<>();
        ArrayList<Movie> movieList = obj.getMovies();
        ArrayList<Series> serieList = obj.getSeries();
        ArrayList<Book> bookList = obj.getBooks();
        allMedia.addAll(movieList);
        allMedia.addAll(serieList);
        allMedia.addAll(bookList);
        for(Media i: allMedia){
            if (i instanceof Book){
                Book book = (Book) i;
                if(book.getTitle().toUpperCase().contains(input) || book.getAuthor().getName().toUpperCase().contains(input)){
                    System.out.println(book.printOut());
                }
            }
            if (i instanceof Movie) {
                Movie movie = (Movie) i;
                if(movie.getTitle().toUpperCase().contains(input) || movie.getDirector().getName().toUpperCase().contains(input)){
                    System.out.println(movie.printOut());
                }
            }
            if (i instanceof Series){
                Series serie = (Series) i;
                if(serie.getTitle().toUpperCase().equals(input) || serie.getDirector().getName().toUpperCase().equals(input)){
                    System.out.println(serie.printOut());
                }
            }
        }
    }

    public void createBook(){
            try {
                System.out.println("Title: ");
                String title = scanner.nextLine();
                Genre genre = addGenre();
                System.out.println("Pages");
                int minutes = scanner.nextInt();
                System.out.println("Author Id: ");
                int authorIdInput = scanner.nextInt();
                scanner.nextLine();
                Book newBook = new Book();
                newBook.setTitle(title);
                newBook.setPages(minutes);
                authorIdInput = checkAuthorExist(authorIdInput);
                newBook.getAuthor().setAuthorId(authorIdInput);
                newBook.setGenre(genre);
                System.out.println("'sending new book to upcoming addBook() function...'");
                //obj.addBook(newMovie);
            } catch (IllegalArgumentException e) {
            }
    }
    public int checkAuthorExist(int authorIdInput){
        ArrayList<Author> allAuthors = obj.getAuthors();
        boolean exist = false;
        for (Author i : allAuthors) {
            if (authorIdInput == i.getAuthorId()) {
                exist = true;
            }
        }
        while (exist == false) {

            System.out.println("Seems like this author does not exist! \n1 = Add new author\n2 = Try a different id");
            Scanner scanner2 = new Scanner(System.in);
            String option = scanner2.nextLine();
            switch (option) {
                case "1":
                    authorIdInput = createAuthor();
                    exist = true;
                    break;
                case "2":
                    System.out.println("Author Id:");
                    authorIdInput = scanner.nextInt();
                    scanner.nextLine();
                    for (Author i : allAuthors) {
                        if (authorIdInput == i.getAuthorId()) {
                            exist = true;
                        }
                    }
                    break;
            }
        }
        return authorIdInput;
    }

}
