package superiterable;

import student.Student;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamSchool {


    public static void main(String[] args) {
        List<Student> roster = Arrays.asList(
                Student.ofNameGpaCourses("Fred", 2.2, "Math", "Physics"),
                Student.ofNameGpaCourses("Jim", 3.2, "Art"),
                Student.ofNameGpaCourses("Sheila", 3.7, "Math", "Physics", "Quantum Mechanics")
        );

        // Monad, fundamental operation is flatMap

        roster.stream()
                .forEach(System.out::println);

        roster.stream()
                .filter(s -> s.getGpa() > 3)
                .flatMap(s -> s.getCourses().stream())
                .forEach(System.out::println);

        Stream<Student> ss = roster.stream();
                ss.filter(s -> s.getCourses().size() < 3)
                .forEach(s -> System.out.println(">> " + s));

//                ss.forEach(System.out::println);

        Arrays.asList("Fred", "Jim", "Sheila").stream()
                .filter(s -> s.length() > 3)
                .map(s -> s.length())
                .forEach(System.out::println);

        IntStream.iterate(1, x -> x + 1)
                .forEach(System.out::println);
    }
}
