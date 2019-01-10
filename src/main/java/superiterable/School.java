package superiterable;

import student.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class School {


    public static void main(String[] args) {
        SuperIterable<Student> roster = new SuperIterable<>(Arrays.asList(
                Student.ofNameGpaCourses("Fred", 2.2, "Math", "Physics"),
                Student.ofNameGpaCourses("Jim", 3.2, "Art"),
                Student.ofNameGpaCourses("Sheila", 3.7, "Math", "Physics", "Quantum Mechanics")
        ));
//        forEvery(roster, s -> System.out.println(s));
//        forEvery(roster, System.out::println);
//        forEvery(filter(roster, s -> s.getCourses().size() < 3), s -> System.out.println(">> " + s));
//        forEvery(filter(Arrays.asList("Fred", "Jim", "Sheila"), s -> s.length() > 3), System.out::println);

        roster.forEach(System.out::println);

        roster
//                .filter(s -> s.getGpa() > 3)
                .flatMap(s -> new SuperIterable<>(s.getCourses()))
                .forEach(System.out::println);

        roster
                .filter(s -> s.getCourses().size() < 3)
                .forEach(s -> System.out.println(">> " + s));

        new SuperIterable<>(Arrays.asList("Fred", "Jim", "Sheila"))
                .filter(s -> s.length() > 3)
                .map(s -> s.length())
                .forEach(System.out::println);
    }
}
