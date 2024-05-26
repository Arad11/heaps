public class MoltOrder implements Comparable<MoltOrder>{
    private String name;
    private int timeNeededToDeliver;
    private int priority;
    private int orderReadyTime;
    private String orderDescription;

    public MoltOrder ( String name , String orderDescription , int orderReadyTime , int
    timeNeededToDeliver , int priority ) {
        if(timeNeededToDeliver > 0 && orderReadyTime > 0) {
            this.name = name;
            this.timeNeededToDeliver = timeNeededToDeliver;
            this.priority = priority;
            this.orderReadyTime = orderReadyTime;
            this.orderDescription = orderDescription;
        }
        else {
            throw new IllegalArgumentException("Values must be postive.");
        }
    }

    public int getOrderReadyTime () {
        return this.orderReadyTime;
    }

    public int getTimeNeededToDeliver () {
        return this.timeNeededToDeliver;
    }

    public String getName() {
        return this.name;
    }

    public String getOrderDescription() {
        return this.orderDescription;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(MoltOrder otherOrder){
        /*
         * This method returns the value zero if (this.priority==otherOrder.priority), 
         * if (this.priority < otherOrder.priority) then it returns a value less than zero 
         * and if (this.priority > otherOrder.priority) then it returns a value greater than zero.
         * if the number is < 0 it means that "this" should be handeled first
         */
        return Integer.compare(this.priority, otherOrder.priority);
    }

    @Override
    public String toString() {
        return "MoltOrder{" +
               "name=" + '\'' + name + '\'' +
               ", orderDescription=" + '\'' + orderDescription + '\'' +
               ", orderReadyTime=" + orderReadyTime +
               ", timeNeededToDeliver=" + timeNeededToDeliver +
               ", priority=" + priority +
               '}';
    }
}
