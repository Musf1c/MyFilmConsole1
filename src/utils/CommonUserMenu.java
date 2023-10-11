package utils;
import bootstrap.Application;
import entity.*;

import java.util.Scanner;

import static entity.Film.*;

public class CommonUserMenu implements Menu {
    @Override
    public void printMenu() {
        System.out.println("1. Поиск фильма по названию. \n" +
                "2. Поиск фильма по году \n" +
                "3. Поиск фильма по стране \n" +
                "4. Поиск фильма по жанру \n" +
                "5. Выставить оценку фильму \n " +
                "6. Вывести мой список фильмов \n" +
                "7. Добавить фильм по id в свой список \n" +
                "8. Удалить фильм по названию из своего списка \n +" +
                "9. Сменить данные о себе");
    }

    @Override
    public void handleMenu(Application application, User user, Scanner scanner) {
        Scanner keyboard = new Scanner(System.in);
        CommonUser common = new CommonUser(user);
        int command = keyboard.nextInt();
        switch (command) {
            case 1 -> {
                keyboard.nextLine();
                System.out.println("Введите название фильма: ");
                String findTitle = keyboard.next();
                for (Film film : application.listFilm.getAll())
                    if (film.getTitle().equals(findTitle)) {
                        System.out.println(film);
                    }
            }
            case 2 -> {
                keyboard.nextLine();
                System.out.println("Введите год выпуска фильма: ");
                String dateCreateFilm = keyboard.next();
                for (Film film : application.listFilm.getAll())
                    if (film.getDate().equals(dateCreateFilm)) {
                        System.out.println(film);
                    }
            }
            case 3 -> {
                keyboard.nextLine();
                System.out.println("Введите страну: ");
                String country = keyboard.next();
                for (Film film : application.listFilm.getAll())
                    if (film.getCountry().equals(country)) {
                        System.out.println(film);
                    }
            }
            case 4 -> {
                keyboard.nextLine();
                System.out.println("Введите жанр: ");
                String genre = keyboard.next();
                for (Film film : application.listFilm.getAll())
                    if (film.getGenre().equals(genre)) {
                        System.out.println(film);
                    }
            }
            case 5 -> {
                keyboard.nextLine();
                System.out.println("Введите название фильма: ");
                String title = keyboard.nextLine();
                System.out.println("Введите оценку от 1 до 100: ");
                int rating = Integer.parseInt(keyboard.nextLine());
                while (!(rating > 0 & rating <= 100))
                {
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
                    if(personalFilms[i].getLoginUser().equals(user.getLogin())) {
                        for (int j = 0; j < films.length && films[j] != null; j++){
                            if(personalFilms[i].getTitleFilm().equals(films[j].getTitle())){
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
                for (int i = 0; i < personalFilms.length & personalFilms[i] != null; i++){
                    if (personalFilms[i].getLoginUser().equals(user.getLogin()) & personalFilms[i].getTitleFilm().equals(titleFilm)){
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
                System.out.println("""
                                    Введите через enter следующие поля:
                                    1. Ник
                                    2. Логин
                                    3. Пароль
                                    4. Роль
                                    """);
                keyboard.nextLine();
                String nickName = keyboard.nextLine();
                String login = keyboard.nextLine();
                String password = keyboard.nextLine();
                UserRole role = UserRole.valueOf(keyboard.nextLine());
                common.updateData(nickName, login, password, role);

                application.listUser.replaceElement(user, common);
            }

        }

    }
}
