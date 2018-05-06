import java.util.concurrent.*;

public class ExecutorCallableWithFuture {

    public static void main(String[] args) {
        Callable<Integer> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                return 123;
            } catch (InterruptedException e) {
                throw new IllegalStateException("task inerrupted", e);
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(task);

        System.out.println("future done? " + future.isDone());

        Integer result = null;
        // next code not run until future get value
        try {
            result = future.get();
            System.out.println("result is set?? " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("future done? " + future.isDone());
        System.out.println("result " + result);
    }
}
