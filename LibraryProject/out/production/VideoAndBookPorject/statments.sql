CREATE TABLE director
(
    id INT(11) NOT NULL AUTO_INCREMENT,
    name VARCHAR(35) NOT NULL DEFAULT '',
    bornDate int(8) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE movie
(
    id INT(11) NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL DEFAULT '',
    minutes int(11) NOT NULL,
    directorId INT(11) NOT NULL,
    genre VARCHAR(35) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_movie_director FOREIGN KEY (directorId) REFERENCES director(id)
);

CREATE TABLE serie
(
    id INT(11) NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL DEFAULT '',
    numOfEpisodes INT(5),
    directorId INT(11) NOT NULL,
    genre VARCHAR(35) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_series_director FOREIGN KEY (directorId) REFERENCES director(id)
);

CREATE TABLE author (
                        name varchar(100),
                        bornDate int,
                        PRIMARY KEY (name)
);

CREATE TABLE book
(
    id INT(11) NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL DEFAULT '',
    pages int(11) NOT NULL,
    authorId INT(11) NOT NULL,
    genre VARCHAR(35) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_book_director FOREIGN KEY (authorId) REFERENCES author(id)
);

UPDATE book
SET genre = 'YOUNG'
WHERE genre = 'YOUNG ADULT';

INSERT INTO director(name, borndate) VALUES
                                         ("Martin Scorsese", 19421117),
                                         ("Francis Ford Coppola", 19390704),
                                         ("Steven Spielberg", 19461218),
                                         ("Quentin Tarantino", 19630327),
                                         ("David Fincher", 19620828),
                                         ("Christopher Nolan", 19700730),
                                         ("James Cameron", 19540816),
                                         ("Alfonso Cuarón", 19611228);

INSERT INTO movie (title, minutes, directorId, genre) VALUES
                                                          ("Goodfellas", 146, 1, "CRIME"),
                                                          ("Taxi Driver", 114, 1, "CRIME"),
                                                          ("Raging Bull", 129, 1, "DRAMA"),
                                                          ("The Departed", 151, 1, "CRIME"),
                                                          ("The Wolf of Wall Street", 180, 1, "CRIME"),
                                                          ("The Godfather", 175, 2, "CRIME"),
                                                          ("The Godfather: Part II", 202, 2, "CRIME"),
                                                          ("Apocalypse Now", 153, 2, "WAR"),
                                                          ("The Conversation", 113, 2, "THRILLER"),
                                                          ("The Outsiders", 91, 2, "DRAMA"),
                                                          ("Jaws", 124, 3, "THRILLER"),
                                                          ("Raiders of the Lost Ark", 115, 3, "ADVENTURE"),
                                                          ("E.T. the Extra-Terrestrial", 115, 3, "SF"),
                                                          ("Jurassic Park", 127, 3, "SF"),
                                                          ("Saving Private Ryan", 169, 3, "WAR"),
                                                          ("Schindler's List", 195, 3, "DRAMA"),
                                                          ("Pulp Fiction", 154, 4, "CRIME"),
                                                          ("Kill Bill: Volume 1", 111, 4, "ACTION"),
                                                          ("Inglourious Basterds", 153, 4, "WAR"),
                                                          ("Django Unchained", 165, 4, "WESTERN"),
                                                          ("Once Upon a Time in Hollywood", 161, 4, "DRAMA"),
                                                          ("The Social Network", 120, 5, "DRAMA"),
                                                          ("The Girl with the Dragon Tattoo", 158, 5, "CRIME"),
                                                          ("Gone Girl", 149, 5, "THRILLER"),
                                                          ("Fight Club", 139, 6, "DRAMA"),
                                                          ("The Prestige", 130, 6, "DRAMA"),
                                                          ("Inception", 148, 6, "SF"),
                                                          ("The Dark Knight", 152, 6, "CRIME"),
                                                          ("Dunkirk", 106, 6, "WAR"),
                                                          ("Avatar", 162, 7, "SF"),
                                                          ("Titanic", 194, 7, "DRAMA"),
                                                          ("The Terminator", 108, 7, "SF"),
                                                          ("Aliens", 137, 7, "SF"),
                                                          ("True Lies", 141, 7, "ACTION"),
                                                          ("Gravity", 91, 8, "SF"),
                                                          ("Children of Men", 109, 8, "SF"),
                                                          ("Harry Potter and the Prisoner of Azkaban", 141, 8, "FANTASY"),
                                                          ("Y Tu Mamá También", 106, 8, "DRAMA"),
                                                          ("Roma", 135, 8, "DRAMA");

INSERT INTO serie (title, numOfEpisodes, directorId, genre) VALUES
                                                                ("The Sopranos", 86, 1, "CRIME"),
                                                                ("The Wire", 60, 1, "CRIME"),
                                                                ("Boardwalk Empire", 56, 1, "CRIME"),
                                                                ("The Crown", 40, 2, "DRAMA"),
                                                                ("Stranger Things", 25, 2, "SF"),
                                                                ("Black Mirror", 19, 2, "SF"),
                                                                ("Band of Brothers", 10, 3, "WAR"),
                                                                ("The Pacific", 14, 3, "WAR"),
                                                                ("The Terror", 10, 3, "WAR"),
                                                                ("Jaws", 3, 4, "THRILLER"),
                                                                ("Raiders of the Lost Ark", 2, 4, "ADVENTURE"),
                                                                ("Indiana Jones and the Temple of Doom", 2, 4, "ADVENTURE"),
                                                                ("The Good, the Bad and the Ugly", 2, 5, "WESTERN"),
                                                                ("A Fistful of Dollars", 2, 5, "WESTERN"),
                                                                ("For a Few Dollars More", 2, 5, "WESTERN"),
                                                                ("The Lord of the Rings: The Fellowship of the Ring", 1, 6, "FANTASY"),
                                                                ("The Lord of the Rings: The Two Towers", 1, 6, "FANTASY"),
                                                                ("The Lord of the Rings: The Return of the King", 1, 6, "FANTASY"),
                                                                ("The Silence of the Lambs", 1, 7, "THRILLER"),
                                                                ("The Terminator", 1, 7, "ACTION"),
                                                                ("Aliens", 1, 7, "SF"),
                                                                ("The Matrix", 1, 8, "SF");

INSERT INTO author (name, bornDate)
VALUES
    ('William Shakespeare', 26041564),
    ('J.K. Rowling', 31071965),
    ('Stephenie Meyer', 24121973),
    ('George R.R. Martin', 20220948),
    ('Agatha Christie', 15091890),
    ('John Green', 24081977),
    ('Suzanne Collins', 10081962),
    ('James Patterson', 22031947),
    ('Dan Brown', 22061964),
    ('E.L. James', 07031963);

INSERT INTO book (title, pages, authorId, genre)
VALUES
    ('Hamlet', 234, 1, 'DRAMA'),
    ('Othello', 178, 1, 'DRAMA'),
    ('Macbeth', 168, 1, 'DRAMA'),
    ('Harry Potter and the Half-Blood Prince', 607, 2, 'FANTASY'),
    ('Harry Potter and the Order of Phoenix', 870, 2, 'FANTASY'),
    ('Harry Potter and the Goblet of Fire', 734, 2, 'FANTASY'),
    ('Breaking Dawn', 756, 3, 'ROMANCE'),
    ('Eclipse', 672, 3, 'ROMANCE'),
    ('Twilight', 498, 3, 'ROMANCE'),
    ('A Game of Thrones', 864, 4, 'FANTASY'),
    ('A Clash of Kings', 768, 4, 'FANTASY'),
    ('A Storm of Swords', 992, 4, 'FANTASY'),
    ('Murder on the Orient Express', 256, 5, 'MYSTERY'),
    ('Death on the Nile', 256, 5, 'MYSTERY'),
    ('And Then There Were None', 288, 5, 'MYSTERY'),
    ('The Fault in Our Stars', 313, 6, 'YOUNG ADULT'),
    ('Paper Towns', 305, 6, 'YOUNG ADULT'),
    ('Looking for Alaska', 221, 6, 'YOUNG ADULT'),
    ('The Hunger Games', 374, 7, 'SF'),
    ('Catching Fire', 391, 7, 'SF'),
    ('Mockingjay', 390, 7, 'SF'),
    ('Along Came a Spider', 416, 8, 'THRILLER'),
    ('Kiss the Girls', 448, 8, 'THRILLER'),
    ('Along Came a Spider', 528, 8, 'THRILLER'),
    ('Inferno', 461, 9, 'THRILLER'),
    ('The Da Vinci Code', 592, 9, 'THRILLER'),
    ('Angels & Demons', 617, 9, 'THRILLER'),
    ('Fifty Shades of Grey', 532, 10, 'EROTICA'),
    ('Fifty Shades Darker', 554, 10, 'EROTICA'),
    ('Fifty Shades Freed', 537, 10, 'EROTICA');