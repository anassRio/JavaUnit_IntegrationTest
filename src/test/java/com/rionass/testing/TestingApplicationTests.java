package com.rionass.testing;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class TestingApplicationTests {

    Calculator underTest = new Calculator();

    @Test
    void itShouldAddTwoNumbers() {
        // given
        int number1 = 20;
        int number2 = 30;

        //when
        int result = underTest.add(number1, number2);

        //then
        assertThat(result).isEqualTo(50);
    }


    class Calculator {
        int add(int a, int b) {
            return a + b;
        }
    }

}
