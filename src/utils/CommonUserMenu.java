package utils;

public class CommonUserMenu implements Menu {
    @Override
    public void printMenu() {
        System.out.println("1. Поиск фильма по названию. \n" +
                "2. Поиск фильма по году \n" +
                "3. Поиск фильма по стране \n" +
                "4. Поиск фильма по жанру \n" +
                "5. Выставить оценку фильму \n " +
                "6. Вывести мой список фильмов \n" +
                "7. Добавить фильм по id \n" +
                "8. Удалить фильм по id \n +" +
                "9. Сменить данные о себе");
    }
}
