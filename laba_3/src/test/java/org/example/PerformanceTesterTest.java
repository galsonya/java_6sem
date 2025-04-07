package org.example;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PerformanceTesterTest {

    @Test
    public void testAddPerformance() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        final int TEST_SIZE = 1000; // Количество операций

        long arrayListStartTime = System.nanoTime();
        for (int i = 0; i < TEST_SIZE; i++) {
            arrayList.add(i);
        }
        long arrayListEndTime = System.nanoTime();
        double arrayListTime = (arrayListEndTime - arrayListStartTime) / 1_000_000.0;

        long linkedListStartTime = System.nanoTime();
        for (int i = 0; i < TEST_SIZE; i++) {
            linkedList.add(i);
        }
        long linkedListEndTime = System.nanoTime();
        double linkedListTime = (linkedListEndTime - linkedListStartTime) / 1_000_000.0;

        System.out.printf("ArrayList add time: %.2f ms%n", arrayListTime);
        System.out.printf("LinkedList add time: %.2f ms%n", linkedListTime);
    }

    @Test
    public void testGetPerformance() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }

        long startTime = System.nanoTime();
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1_000_000.0;

        System.out.printf("Get time: %.2f ms%n", time);
    }

    @Test
    public void testRemovePerformance() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }

        long startTime = System.nanoTime();
        for (int i = list.size() - 1; i >= 0; i--) {
            list.remove(i);
        }
        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1_000_000.0;

        System.out.printf("Remove time: %.2f ms%n", time);
    }
}
