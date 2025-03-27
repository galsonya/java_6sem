package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
public class TestContainer {
    @Test
    public void testAddGet() {
        Container container = new Container();
        container.add(10);
        container.add(20);

        assertEquals(10, container.get(0));
        assertEquals(20, container.get(1));
        assertEquals(2, container.size());
    }

    @Test
    public void testRemove() {
        Container container = new Container();
        container.add(10);
        container.add(20);
        container.add(30);

        container.remove(1);

        assertEquals(2, container.size());
        assertEquals(10, container.get(0));
        assertEquals(30, container.get(1));
    }

    @Test
    public void testIsEmpty() {
        Container container = new Container();
        assertTrue(container.isEmpty());

        container.add(1);
        assertFalse(container.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetWithInvalidIndex() {
        Container container = new Container();
        container.add(1);
        container.get(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveWithInvalidIndex() {
        Container container = new Container();
        container.remove(0);
    }

    @Test
    public void testDynamicExpansion() {
        Container container = new Container(2);
        container.add(1);
        container.add(2);
        container.add(3);

        assertEquals(3, container.size());
        assertEquals(3, container.get(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeInitialCapacity() {
        new Container(-1);
    }
}