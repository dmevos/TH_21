import static java.lang.Thread.sleep;
public class BuildCar implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= Main.COUNT_BUYERS; i++) {
            try {
                sleep(Main.BUILD_TIME);
            } catch (InterruptedException e) {
                return;
            }
            synchronized (Main.cars) {
                Main.cars.add("Электрокар №" + i);
                System.out.println("Производитель UZ_Daewoo выпустил cамый крутой электрокар №" + i);
                Main.cars.notify();
            }
        }
    }
}
