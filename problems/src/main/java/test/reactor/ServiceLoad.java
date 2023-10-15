package test.reactor;

public class ServiceLoad {

    public String compute(String input) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Finised: " + input + " current time: " + System.currentTimeMillis();
    }
}
