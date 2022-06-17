import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class test {

    @Test
    void validate() {
        assertEquals(false, Main.validate("12312312312"));
    }

    @Test
    void validate2() {
        assertEquals(true, Main.validate("89060177631"));
    }

    @Test
    void checkPesel() {
        ArrayList<People> people = new ArrayList<People>();
        people.add(new People("Warsaw", "Jan", "Kowalski", "89060177631"));
        people.add(new People("Pozn", "Roman", "Barabsz", "66666666666"));

        assertEquals(true, Main.checkPesel(people, "89060177631"));
    }
}
