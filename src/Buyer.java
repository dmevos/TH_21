public class Buyer implements Runnable {
    @Override
    public void run() {
        System.out.printf("Покупатель %s зашел в салон\n", Thread.currentThread().getName());
        synchronized (Main.cars) {
            if (Main.cars.isEmpty()) {
                System.out.println("Машин нет");
                try {
                    Main.cars.wait();
                } catch (InterruptedException e) {
                    return;
                }
            }
            System.out.printf("Очередной олигарх (Покупатель %s) приобрел люкс-авто - \"%s\"\n", Thread.currentThread().getName(), Main.cars.remove(0));
        }
    }
}
