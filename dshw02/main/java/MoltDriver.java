public class MoltDriver implements Comparable<MoltDriver>{
    private int id;
    private String name;
    private int nextAvailableTimeForDelivery;
    private int totalOrdersDelivered;

    public MoltDriver ( int id , String name , int nextAvailableTimeForDelivery ) {
        if (nextAvailableTimeForDelivery < 0) {
            throw new IllegalArgumentException("Next available time must be non-negative.");
        }
        this.id = id;
        this.name = name;
        this.nextAvailableTimeForDelivery = nextAvailableTimeForDelivery;
        this.totalOrdersDelivered = 0;
    }

    public void incrementTotalOrdersDelivered() {
        this.totalOrdersDelivered++;
    }

    public int getTotalOrdersDelivered() {
        return totalOrdersDelivered;
    }

    public int getNextAvailableTimeForDelivery () {
        return this.nextAvailableTimeForDelivery;
    }

    public void setNextAvailableTimeForDelivery(int time) {
        if (time > 0) {
            this.nextAvailableTimeForDelivery = time;
        } else {
            throw new IllegalArgumentException("Time must be positive.");
        }
    }

    public String getName () {
        return this.name;
    }

    @Override
    public String toString() {
        return "MoltDriver{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", nextAvailableTimeForDelivery=" + nextAvailableTimeForDelivery +
               ", totalOrdersDelivered=" + totalOrdersDelivered +"}";
    }

    @Override
    public int compareTo ( MoltDriver otherDriver ) {
          /*
         * This method returns the value zero if (this.nextAvailableTimeForDelivery==otherOrder.priority), 
         * if (this.nextAvailableTimeForDelivery < otherOrder.nextAvailableTimeForDelivery) then it returns a value less than zero 
         * and if (this.nextAvailableTimeForDelivery > otherOrder.nextAvailableTimeForDelivery) then it returns a value greater than zero.
            if return nuber < 0 it means that "This" should be haneled first
         */
        return  Integer.compare(this.nextAvailableTimeForDelivery, otherDriver.nextAvailableTimeForDelivery);
    }
}