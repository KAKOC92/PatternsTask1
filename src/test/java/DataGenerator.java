import com.github.javafaker.Faker;
import lombok.Value;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {                               //Генератор даты
    private DataGenerator() {
    }

    public static String generateData(int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {                   // Генеротор выбора города
        var cities = new String[]{"Майкоп", "Горно-Алтайск", "Уфа", "Улан-Удэ", "Махачкала", "Магас", "Нальчик",
                "Элиста", "Черкесск", "Петрозаводск", "Сыктывкар", "Симферополь", "Йошкар-Ола", "Саранск", "Якутск",
                "Владикавказ", "Казань", "Кызыл", "Ижевск", "Абакан", "Грозный", "Чебоксары",
                "Барнаул", "Чита", "Петропавловск-Камчатский", "Краснодар", "Красноярск", "Пермь", "Владивосток",
                "Ставрополь", "Хабаровск", "Благовещенск", "Архангельск", "Астрахань", "Белгород", "Брянск", "Владимир",
                "Волгоград", "Вологда", "Воронеж", "Иваново", "Иркутск", "Калининград", "Калуга", "Кемерово",
                "Киров", "Кострома", "Курган", "Курск", "Липецк", "Магадан", "Москва", "Мурманск", "Нижний " +
                "Новгород", "Великий Новгород", "Новосибирск", "Омск", "Оренбург", "Орёл", "Пенза", "Псков", "Ростов-" +
                "на-Дону", "Рязань", "Самара", "Саратов", "Южно-Сахалинск", "Екатеринбург", "Смоленск", "Тамбов", "Тверь",
                "Томск", "Тула", "Тюмень", "Ульяновск", "Херсон", "Челябинск", "Ярославль", "Москва", "Санкт-Петербург",
                "Биробиджан", "Нарьян-Мар", "Ханты-Мансийск", "Анадырь", "Салехард"};
        return  cities [new Random().nextInt(cities.length)];

    }
    public static String generateName (String locale) {             //Генератор имя и фамилии пользователя
        var faker = new Faker(new Locale("ru"));
        return  faker.name().lastName() +" " +faker.name().lastName();
    }

    public  static String generatePhoneNumber (String locale) {     //Генератор номера телефона
        var faker = new Faker(new Locale("ru"));
        return  faker.phoneNumber().phoneNumber();
    }

    public static  class Registration {
        private Registration(){
        }

        public static User generateUser(String locale){
            return new User(generateCity(), generateName(locale), generatePhoneNumber(locale));
        }
    }

    @Value
    public static  class  User{                                      //Какие данные есть у пользователя
        String city;
        String name;
        String PhoneNumber;
    }
}

