import java.util.concurrent.*;

public class ExecutorFutureWillShutdown {
    public static void main(String[] args) {
        Callable<Integer> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                return 123;
            } catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(task);

        System.out.println("future done? " + future.isDone());

        Integer result = null;

        try {
            executor.shutdownNow();
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("feture done? " + future.isDone());
        System.out.println("result: "+result);

    }
}
