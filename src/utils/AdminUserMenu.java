package utils;
import bootstrap.Application;
import entity.*;

import java.util.Scanner;

import static entity.Film.*;

public class AdminUserMenu implements Menu {

    @Override
    public void printMenu() {
        System.out.println("1. Поиск фильма по названию. \n" +
                "2. Поиск фильма по году \n" +
                "3. Поиск фильма по стране \n" +
                "4. Поиск фильма по жанру \n" +
                "5. Выставить оценку фильму \n" +
                "6. Вывести мой список фильмов \n" +
                "7. Добавить фильм по id \n" +
                "8. Удалить фильм по id \n" +
                "9. Сменить данные о себе \n" +
                "10. Добавить фильм в общий список \n" +
                "11. Удалить из общего списка по id");
    }

    @Override
    public void handleMenu(Application application, User user, Scanner scanner) {
        Scanner keyboard = new Scanner(System.in);
        AdminUser common = new AdminUser(user);
        int command = keyboard.nextInt();
        switch (command) {
            case 1 -> {
                keyboard.nextLine();
                System.out.println("Введите название фильма: ");
                String findTitle = keyboard.next();
                Film[] films = application.listFilm.getAll();
                for (int i = 0; i < films.length && films[i] != null; i++) {
                    if (films[i].getTitle().equals(findTitle)) {
                        System.out.println(films[i]);
                    }
                }
            }
            case 2 -> {
                keyboard.nextLine();
                System.out.println("Введите год выпуска фильма: ");
                String dateCreateFilm = keyboard.next();
                Film[] films = application.listFilm.getAll();
                for (int i = 0; i < films.length && films[i] != null; i++) {
                    if (films[i].getDate().equals(dateCreateFilm)) {
                        System.out.println(films[i]);
                    }
                }
            }
            case 3 -> {
                keyboard.nextLine();
                System.out.println("Введите страну: ");
                String country = keyboard.next();
                Film[] films = application.listFilm.getAll();
                for (int i = 0; i < films.length && films[i] != null; i++) {
                    if (films[i].getCountry().equals(country)) {
                        System.out.println(films[i]);
                    }
                }
            }
            case 4 -> {
                keyboard.nextLine();
                System.out.println("Введите жанр: ");
                String genre = keyboard.next();
                Film[] films = application.listFilm.getAll();
                for (int i = 0; i < films.length && films[i] != null; i++) {
                    if (films[i].getGenre().equals(genre)) {
                        System.out.println(films[i]);
                    }
                }
            }
            case 5 -> {
                keyboard.nextLine();
                System.out.println("Введите название фильма: ");
                String title = keyboard.nextLine();
                System.out.println("Введите оценку от 1 до 100: ");
                int rating = Integer.parseInt(keyboard.nextLine());
                while (!(rating > 0 & rating <= 100)) {
                    System.out.println("Введите оценку от 1 до 100: ");
                    rating = Integer.parseInt(keyboard.nextLine());
                }
                FilmRating newFilmRating = new FilmRating(getIdFilmForTitle(application.listFilm, title), title, user.getLogin(), rating);
                application.filmRatingList.insert(newFilmRating);

            }
            case 6 -> {
                PersonalFilm[] personalFilms = application.personalFilmList.getAll();
                Film[] films = application.listFilm.getAll();

                for (int i = 0; i < personalFilms.length && personalFilms[i] != null; i++) {
                    if (personalFilms[i].getLoginUser().equals(user.getLogin())) {
                        for (int j = 0; j < films.length && films[j] != null; j++) {
                            if (personalFilms[i].getTitleFilm().equals(films[j].getTitle())) {
                                System.out.println(films[j].toString());
                            }
                        }
                    }
                }
            }
            case 7 -> {
                PersonalFilm[] personalFilms = application.personalFilmList.getAll();
                Film[] films = application.listFilm.getAll();
                List<String> listTitle = new List<>(new String[100]);
                String[] massTitle = listTitle.getAll();
                System.out.println("Введите id фильма");
                int idFilm = keyboard.nextInt();
                while (!isHadIdFilmInIdFilmList(films, idFilm)) {
                    System.out.println("Такого id нет");
                    idFilm = keyboard.nextInt();
                }
                if (isHadIdFilmInIdFilmList(films, idFilm)) {
                    Film film = getFilmForIdFilm(films, idFilm);
                    for (int i = 0; i < personalFilms.length && personalFilms[i] != null; i++) {
                        if (user.getLogin().equals(personalFilms[i].getLoginUser())) {
                            listTitle.insert(personalFilms[i].getTitleFilm());
                        }
                    }
                    int count = 0;
                    int it = 0;
                    for (int j = 0; j < massTitle.length && massTitle[j] != null; j++) {
                        if (massTitle[j].equals(film.getTitle())) {
                            System.out.println("Такой фильм есть!");
                        } else {
                            count++;
                        }
                        it = j;
                    }
                    if (count == it + 1) {
                        PersonalFilm personalFilm = new PersonalFilm(user.getLogin(), film.getTitle());
                        application.personalFilmList.insert(personalFilm);
                        application.personalFilmList.print();
                    }
                }
            }


            case 8 -> {
                PersonalFilm[] personalFilms = application.personalFilmList.getAll();
                System.out.println("Введите название фильма");
                keyboard.nextLine();
                String titleFilm = keyboard.nextLine();
                int count = 0;
                int index = 0;
                for (int i = 0; i < personalFilms.length & personalFilms[i] != null; i++) {
                    if (personalFilms[i].getLoginUser().equals(user.getLogin()) & personalFilms[i].getTitleFilm().equals(titleFilm)) {
                        application.personalFilmList.remove(personalFilms[i]);
                        System.out.println("Фильм удален из вашего списка");
                        application.personalFilmList.print();

                    } else {
                        count++;
                    }
                    index = i;
                }
                if (count == index + 1) {

                    System.out.println("У вас нет фильма с таким названием");
                }

            }
            case 9 -> {

                keyboard.nextLine();
                System.out.println("Введите ник :");
                String nickName = keyboard.nextLine();
                System.out.println("Введите логин: ");
                String login = keyboard.nextLine();
                System.out.println("Введите пароль: ");
                String password = keyboard.nextLine();

                UserRole role = UserRole.ADMIN;
                common.updateData(nickName, login, password, role);

                application.listUser.replaceElement(user, common);
            }
            case 10 -> {
                Film[] films = application.listFilm.getAll();
                int IdFilm1;
                int IdFilm;
                while (true) {
                    IdFilm1 = (int) (Math.random() * 100000);
                    if (!isHadIdFilmInIdFilmList(films, IdFilm1)) {
                        IdFilm = IdFilm1;
                        break;
                    }
                }
                keyboard.nextLine();
                System.out.println("Введите название фильма: ");
                String title = keyboard.nextLine();

                System.out.println("Введите жанр фильма: ");
                String genre = keyboard.nextLine();
                System.out.println("Введите страну производства фильма: ");
                String country = keyboard.nextLine();
                System.out.println("Введите год выпуска: ");
                String date = keyboard.nextLine();
                Film film = builder().buildIdFilm(IdFilm)
                        .buildTitle(title)
                        .buildGenre(genre)
                        .buildCounty(country)
                        .buildDate(date)
                        .build();
                application.listFilm.insert(film);
                application.listFilm.print();

            }
            case 11 -> {
                Film[] films = application.listFilm.getAll();
                int IdFilm1 = 0;
                int IdFilm;
                System.out.println("Введите id: ");
                while (true) {
                    IdFilm = keyboard.nextInt();
                    if (isHadIdFilmInIdFilmList(films, IdFilm1)) {
                        IdFilm = IdFilm1;
                        break;
                    }
                }


            }

        }
    }
}
