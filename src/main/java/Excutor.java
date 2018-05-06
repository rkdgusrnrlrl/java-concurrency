import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Excutor {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {

            try {
                System.out.println("attemp to shutdown executor");
                executor.shutdown();
                executor.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.out.println("tasks interrupted");
                e.printStackTrace();
            } finally {
                if (!executor.isTerminated()) {
                    System.out.println("cancel non-finished tasks");
                }
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        });
    }
}
