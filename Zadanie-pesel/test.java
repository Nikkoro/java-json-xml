import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class test {

    @Test
    void validate() {
        boolean t1 = Main.validate("12312312312");

        assertEquals(false, t1);
    }

    @Test
    void validate2() {
        boolean t1 = Main.validate("89060177631");

        assertEquals(true, t1);
    }

    @Test
    void checkPesel() {
        ArrayList<People> people = new ArrayList<People>();
        people.add(new People("Warsaw", "Jan", "Kowalski", "89060177631"));
        people.add(new People("Pozn", "Roman", "Barabsz", "66666666666"));
        boolean t2 = Main.checkPesel(people, "89060177631");

        assertEquals(true, t2);
    }
}
