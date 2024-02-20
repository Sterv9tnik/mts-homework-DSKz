import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestCalculation {

    @Test
    @DisplayName("Положительный тест. Сложение переменных")
    public void successAdding() {
        int a = 2;
        int b = 4;
        int c = 6;

        assertThat(a + b).isEqualTo(c);
    }

    @Test
    @DisplayName("Отрицательный тест. Сложение переменных")
    public void failureAdding() {
        int a = 2;
        int b = 3;
        int c = 6;

        assertThat(a + b).isEqualTo(c);
    }
}
