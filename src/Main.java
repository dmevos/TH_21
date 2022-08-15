import java.util.LinkedList;
import java.util.List;
import static java.lang.Thread.sleep;

public class Main {
    static final int COUNT_CARS = 5;
    static final int BUILD_TIME = 700;
    static final int WAIT_TO_BUY = 300;

    public static void main(String[] args) {
        List<String> cars = new LinkedList<>();

        //поток изготовление автомобилей
        new Thread(() -> {
            for (int i = 1; i <= COUNT_CARS; i++) {
                try {
                    sleep(BUILD_TIME);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (cars) {
                    cars.add("Электрокар №" + i);
                    System.out.println("Производитель UZ_Daewoo выпустил cамый крутой электрокар №" + i);
                    cars.notify();
                }

            }

        }).start();

        // поток продажа авто
        new Thread(() -> {
            int i = 1;
            while (i <= COUNT_CARS) {
                System.out.printf("Покупатель %d зашел в салон\n", i);
                try {
                    sleep(WAIT_TO_BUY);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (cars) {
                    if (cars.isEmpty()) {
                        System.out.println("Машин нет");
                        try {
                            cars.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.printf("Очередной олигарх (Покупатель %d) приобрел люкс-авто - \"%s\"\n", i, cars.remove(0));
                }
                i++;
            }

        }).start();

    }
}