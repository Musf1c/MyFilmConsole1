package utils;

import entity.*;

import java.io.*;

public class FileUtils {

    static String[] getPartsOfLine(String line) {

        return line.split(", ");
    }

    public static void readFileUser(List<User> listUser, String fileName) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] partsOfLine = getPartsOfLine(line);

                UserRole userRole = switch (partsOfLine[3]) {
                    case "COMMON" -> UserRole.COMMON;
                    case "ADMIN" -> UserRole.ADMIN;
                    default -> throw new Exception("Неверные данные");
                };

                User user = new User(partsOfLine[0], partsOfLine[1], partsOfLine[2], userRole);


                listUser.insert(user);
            }

            listUser.print();
            bufferedReader.close();
            fileReader.close();

        } catch (IOException e) {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (Exception er) {
                System.out.println("Произошла ошибка");
            }
            throw new RuntimeException("Такой файл не найден");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeDataToFileUser(List<User> listUser, String fileName) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(fileWriter);

            User[] users = listUser.getAll();

            for (int i = 0; i < listUser.getSize(); i++) {
                bufferedWriter.write(users[i] + "\n");
            }

            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFileFilm(List<Film> listFilm, String fileName, List<FilmRating> filmRatingList) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] partsOfLine = getPartsOfLine(line);
                Film film = new Film.FilmBuilder().buildIdFilm(Integer.parseInt(partsOfLine[0]))
                        .buildTitle(partsOfLine[1])
                        .buildGenre(partsOfLine[2])
                        .buildCounty(partsOfLine[3])
                        .buildDate(partsOfLine[4])
                        .buildRating(ratingCount(filmRatingList, partsOfLine[2]))
                        .build();
//                Film film = new FilmBuilder(Integer.parseInt(partsOfLine[0]), partsOfLine[1], partsOfLine[2], partsOfLine[3], partsOfLine[4],
//                        ratingCount(filmRatingList, partsOfLine[2]));

                listFilm.insert(film);
            }

                listFilm.print();
                bufferedReader.close();
                fileReader.close();

            } catch(IOException e){
                try {
                    bufferedReader.close();
                    fileReader.close();
                } catch (Exception er) {
                    System.out.println("Произошла ошибка");
                }
                throw new RuntimeException("Такой файл не найден");
            } catch(Exception e){
                System.out.println(e.getMessage());
            }

    }

    public static void writeDataToFileFilm(List<Film> listFilm, String fileName) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(fileWriter);

            Film[] films = listFilm.getAll();

            for (int i = 0; i < listFilm.getSize(); i++) {
                bufferedWriter.write(films[i] + "\n");
            }

            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readFileFilmRating(List<FilmRating> listFilmRating, String fileName) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] partsOfLine = getPartsOfLine(line);


                FilmRating filmRating = new FilmRating(Integer.parseInt(partsOfLine[1]), partsOfLine[2], partsOfLine[0], Integer.parseInt(partsOfLine[3]));


                listFilmRating.insert(filmRating);
            }

            listFilmRating.print();
            bufferedReader.close();
            fileReader.close();

        } catch (IOException e) {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (Exception er) {
                System.out.println("Произошла ошибка");
            }
            throw new RuntimeException("Такой файл не найден");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeDataToFileFilmRating(List<FilmRating> listFilmRating, String fileName) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(fileWriter);

            FilmRating[] filmRatings = listFilmRating.getAll();

            for (int i = 0; i < listFilmRating.getSize(); i++) {
                bufferedWriter.write(filmRatings[i] + "\n");
            }

            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static int ratingCount(List<FilmRating> filmRatingList, String title) {
        int count = 0;
        int sumRating = 0;
        if (filmRatingList.getSize() != 0){
            System.out.println( "лист рейтинга не нал");
        }
        FilmRating[] dfs = filmRatingList.getAll();
        System.out.println(dfs[0].getTitle());
        for (int i = 0; i < dfs.length; i++) {
           if(dfs[i].getTitle().equals(title)) {
               sumRating += dfs[i].getRating();
               ++count;
           }
        }
        if (count == 0) {
            return 0;
        }

        return (int) sumRating/count;
    }

}

