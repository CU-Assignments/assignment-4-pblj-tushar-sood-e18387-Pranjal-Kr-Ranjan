package pkg5;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private static final int TOTAL_SEATS = 10;
    private boolean[] seats = new boolean[TOTAL_SEATS];
    private Lock lock = new ReentrantLock();

    public void bookSeat(String customerType, int seatNumber) {
        lock.lock();
        try {
            if (!seats[seatNumber]) {
                System.out.println(customerType + " booked seat number " + seatNumber);
                seats[seatNumber] = true;
            } else {
                System.out.println(customerType + " tried to book an already booked seat " + seatNumber);
            }
        } finally {
            lock.unlock();
        }
    }
}

class BookingThread extends Thread {
    private TicketBookingSystem system;
    private String customerType;
    private int seatNumber;

    public BookingThread(TicketBookingSystem system, String customerType, int seatNumber) {
        this.system = system;
        this.customerType = customerType;
        this.seatNumber = seatNumber;
    }

    @Override
    public void run() {
        system.bookSeat(customerType, seatNumber);
    }
}

public class Main {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();

        // Regular customers
        BookingThread regular1 = new BookingThread(system, "Regular", 1);
        BookingThread regular2 = new BookingThread(system, "Regular", 1); // Trying to book the same seat

        // VIP customers with higher priority
        BookingThread vip1 = new BookingThread(system, "VIP", 2);
        vip1.setPriority(Thread.MAX_PRIORITY); // Giving VIP highest priority
        BookingThread vip2 = new BookingThread(system, "VIP", 3);
        vip2.setPriority(Thread.MAX_PRIORITY); // Giving VIP highest priority

        // Start the threads
        regular1.start();
        regular2.start();
        vip1.start();
        vip2.start();

        try {
            regular1.join();
            regular2.join();
            vip1.join();
            vip2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
