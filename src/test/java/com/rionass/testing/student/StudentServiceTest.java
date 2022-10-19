package com.rionass.testing.student;

import com.rionass.testing.student.exception.BadRequestException;
import com.rionass.testing.student.exception.StudentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.rionass.testing.student.Gender.MALE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private StudentService underTest;

    private static Long STUDENT_ID = 1L;

    @BeforeEach
    void setUp() {

        underTest = new StudentService(studentRepository);
    }

    @Test
    void canGetAllStudent() {

        //when
        underTest.getAllStudent();
        //then
        verify(studentRepository).findAll();
    }

    @Test
    void canAddStudent() {

        //Given
        Student student = new Student(
                "Anass",
                "anass@email.fr",
                MALE);

        //When
        underTest.addStudent(student);

        //then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        verify(studentRepository)
                .save(studentArgumentCaptor.capture());
        Student captorValue = studentArgumentCaptor.getValue();

        assertThat(captorValue).isEqualTo(student);

    }

    @Test
    void willThrowWhenEmailIsTaken() {

        //Given
        Student student = new Student(
                "Anass",
                "anass@email.fr",
                MALE);

        given(studentRepository.selectExistsEmail(anyString()))
                .willReturn(true);

        //When
        //then
        assertThatThrownBy(() -> underTest.addStudent(student))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Email : " + student.getEmail() + " Already taken");

        verify(studentRepository,never()).save(any());

    }

    @Test
    void deleteStudent() {

        //Given
        given(studentRepository.existsById(STUDENT_ID))
                .willReturn(true);

        //when
        underTest.deleteStudent(STUDENT_ID);

        //then
        ArgumentCaptor<Long> studentArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(studentRepository).deleteById(studentArgumentCaptor.capture());
        Long captorValue = studentArgumentCaptor.getValue();
        assertThat(captorValue).isEqualTo(STUDENT_ID);

    }

    @Test
    void willThrowsIdDoesNotExist(){

        //given
        given(studentRepository.existsById(STUDENT_ID))
                .willReturn(false);

        //when
        //Then
        assertThatThrownBy(() -> underTest.deleteStudent(STUDENT_ID))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessage("Student with ID : "+ STUDENT_ID + " does not exists");

        verify(studentRepository, never()).deleteById(any());
    }
}