package miu.edu.cs.cs425.carRentalWebApp.controller;

public class CounterHelper {
    private int itemCount = 0;

    public int initializeAndGet(int value) {
        itemCount = value;
        return itemCount;
    }

    public int incrementAndGet() {
        itemCount++;
        return itemCount;
    }
}
