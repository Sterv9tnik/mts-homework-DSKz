import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestCalculation {

    @Test
    public void successAdding() {
        int a = 2;
        int b = 4;
        int c = 6;

        assertThat(a + b).isEqualTo(c);
    }
}
