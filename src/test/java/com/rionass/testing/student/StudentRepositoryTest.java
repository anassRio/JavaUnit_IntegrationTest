package com.rionass.testing.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.rionass.testing.student.Gender.MALE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    void selectExistsEmail() {
        //Given
        String email = "anass@email.com";
        Student student = new Student(
                "Anass",
                email,
                MALE);

        underTest.save(student);

        //When
        Boolean expected = underTest.selectExistsEmail(email);

        //Then
        assertThat(expected).isTrue();

    }

    @Test
    void checkNotExistsEmail() {
        //Given
        String email = "anass@email.com";

        //When
        Boolean expected = underTest.selectExistsEmail(email);

        //Then
        assertThat(expected).isFalse() ;

    }
}