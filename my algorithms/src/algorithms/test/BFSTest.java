package algorithms.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class BFSTest {

    private static final Point testPoint = new Point(1, 2);

    @Test
    public void shouldReturnNewPointAndXcordinatetLesserOnOnePointWhenLeftMove() {
        assertTrue(new Point(0, 2), left(testPoint));

}
