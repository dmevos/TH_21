import java.util.LinkedList;
import java.util.List;
import static java.lang.Thread.sleep;
public class Main {
    static final int BUILD_TIME = 700;
    static final int COUNT_BUYERS = 10; //количество покупателей (столько же и потоков-покупателей)
    static final List<String> cars = new LinkedList<>();

    public static void main(String[] args) throws InterruptedException {
        //поток изготовление автомобилей
        Thread buildCar = new Thread(new BuildCar());
        buildCar.start();

        // Запускаем в Luxury салон покупателей
        for (int i = 1; i <= COUNT_BUYERS ; i++) {
            Thread q = (new Thread(new Buyer()));
            q.setName(Integer.toString(i));
            q.start();
            sleep(500);
        }
    }
}