import java.util.Random;

public class AirportSimulation {

    public static void main(String[] args) {

        LinkedQueue<Integer> landingQueue = new LinkedQueue<>();
        LinkedQueue<Integer> takeoffQueue = new LinkedQueue<>();

        Random rand = new Random();

        int simulationTime = 100;
        double landingRate = 0.3;
        double takeoffRate = 0.2;

        int landingCount = 0;
        int takeoffCount = 0;

        int totalLandingWait = 0;
        int totalTakeoffWait = 0;

        int totalLandingQueueLength = 0;
        int totalTakeoffQueueLength = 0;

        for (int time = 0; time < simulationTime; time++) {

            if (rand.nextDouble() < landingRate) {
                landingQueue.enqueue(time);
                System.out.println("Plane arrived for landing at time " + time);
            }

            if (rand.nextDouble() < takeoffRate) {
                takeoffQueue.enqueue(time);
                System.out.println("Plane arrived for takeoff at time " + time);
            }

            totalLandingQueueLength += landingQueue.size();
            totalTakeoffQueueLength += takeoffQueue.size();

            if (!landingQueue.isEmpty()) {
                int arrivalTime = landingQueue.dequeue();
                int waitTime = time - arrivalTime;

                totalLandingWait += waitTime;
                landingCount++;

                System.out.println("Plane landed at time " + time +
                        " waited " + waitTime + " minutes");

            } else if (!takeoffQueue.isEmpty()) {

                int arrivalTime = takeoffQueue.dequeue();
                int waitTime = time - arrivalTime;

                totalTakeoffWait += waitTime;
                takeoffCount++;

                System.out.println("Plane took off at time " + time +
                        " waited " + waitTime + " minutes");
            }
        }

        System.out.println("\n===== Simulation Report =====");

        System.out.println("Planes landed: " + landingCount);
        System.out.println("Planes taken off: " + takeoffCount);

        double avgLandingQueue =
                (double) totalLandingQueueLength / simulationTime;

        double avgTakeoffQueue =
                (double) totalTakeoffQueueLength / simulationTime;

        System.out.println("Average landing queue length: " + avgLandingQueue);
        System.out.println("Average takeoff queue length: " + avgTakeoffQueue);

        if (landingCount > 0) {
            System.out.println("Average landing wait time: "
                    + (double) totalLandingWait / landingCount);
        }

        if (takeoffCount > 0) {
            System.out.println("Average takeoff wait time: "
                    + (double) totalTakeoffWait / takeoffCount);
        }
    }
}