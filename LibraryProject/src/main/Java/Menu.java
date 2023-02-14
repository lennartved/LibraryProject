import java.util.Scanner;


public class Menu {
    public void run(){
        Libraryprogram program = new Libraryprogram();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while(running){
            System.out.println("1 = Movie\n2 = Series\n3 = Book\n4 = Search all by keyword\n5 = Delete content\n6 = Exit");
            String optionInput = scanner.nextLine();
            switch (optionInput){
                case "1":
                    System.out.println("1 = Show all \n2 = Add\n3 = Sort by genre\n4 = Modify\n5 = Menu");
                    optionInput = scanner.nextLine();
                    switch (optionInput){
                        case "1": program.showAllByCat("movie");
                            break;
                        case "2": program.createMovie();
                            break;
                        case "3": program.showMovieByGenre();
                            break;
                        case "4": program.modifyMovie();
                            break;
                        case "5":
                            break;
                    }
                    break;
                case "2":
                    System.out.println("1 = Show all \n2 = Add\n3 = Sort by genre\n4 = Modify\n6 = Menu");
                    optionInput = scanner.nextLine();
                    switch (optionInput){
                        case "1":  program.showAllByCat("series");
                            break;
                        case "2": program.createSerie();
                            break;
                        case "3": program.showSerieByGenre();
                            break;
                        case "4": program.modifySerie();
                            break;
                        case "5":
                            break;
                    }
                    break;
                case "3":
                    System.out.println("1 = Show all \n2 = Add\n3 = Sort by genre\n4 = Modify\n5 = Menu");
                    optionInput = scanner.nextLine();
                    switch (optionInput){
                        case "1": program.showAllByCat("book");
                            break;
                        case "2": program.createBook();
                            break;
                        case "3": program.showBookByGenre();
                            break;
                        case "4": program.modifyBook();
                            break;
                        case "5":
                            break;
                    }
                    break;
                case "4": program.keywordSearch();
                    break;
                case "5": program.delete();
                    break;
                case "6": running = false;
                    break;
            }
        }
    }
}

