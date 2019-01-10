package student;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@FunctionalInterface
interface StudentCriterion {
//    void doStuff();
    boolean test(Student s);
}

interface Silly {
    boolean odd(Student s);
}

class SmartCriterion implements StudentCriterion {
    @Override
    public boolean test(Student s) {
        return s.getGpa() > 3.0;
    }
}

class EnthusiasticCriterion implements StudentCriterion {
    @Override
    public boolean test(Student s) {
        return s.getCourses().size() > 1;
    }
}

public class School {
    public static void showAll(List<Student> ls) {
        for (Student s : ls) {
            System.out.println("> " + s);
        }
        System.out.println("-----------------------");
    }

    public static List<Student> getStudentsByCriterion(List<Student> ls, StudentCriterion crit) {
        List<Student> results = new ArrayList<>();

        for (Student s : ls) {
            if (crit.test(s)) {
                results.add(s);
            }
        }
        return results;
    }

    //    public static List<Student> getSmartStudents(List<Student> ls, double threshold) {
//        List<Student> results = new ArrayList<>();
//
//        for (Student s : ls) {
//            if (s.getGpa() > threshold) {
//                results.add(s);
//            }
//        }
//        return results;
//    }
//
//    public static List<Student> getEnthusiasticStudents(List<Student> ls, int threshold) {
//        List<Student> results = new ArrayList<>();
//
//        for (Student s : ls) {
//            if (s.getCourses().size() > threshold) {
//                results.add(s);
//            }
//        }
//        return results;
//    }
//
    public static void main(String[] args) {
        List<Student> roster = Arrays.asList(
                Student.ofNameGpaCourses("Fred", 2.2, "Math", "Physics"),
                Student.ofNameGpaCourses("Jim", 3.2, "Art"),
                Student.ofNameGpaCourses("Sheila", 3.7, "Math", "Physics", "Quantum Mechanics")
        );
        showAll(roster);

//        forEvery(getSmartStudents(roster, 3.5));
//        forEvery(getEnthusiasticStudents(roster, 1));
        showAll(getStudentsByCriterion(roster, new SmartCriterion()));
        showAll(getStudentsByCriterion(roster, new EnthusiasticCriterion()));

        // anonymous inner class
//        forEvery(filter(roster, new
//                /*class EnthusiasticCriterion implements */ StudentCriterion() {
//                    @Override
//                    public boolean test(Student s) {
//                        return s.getCourses().size() < 3;
//                    }
//                }
//        ));

//        forEvery(filter(roster, /*new
//                StudentCriterion() *//*{*/
//                    /*@Override
//                    public boolean test*/(Student s) -> {
//                        return s.getCourses().size() < 3;
//                    }
//                /*}*/
//        ));
//        forEvery(filter(roster, (Student s) -> {
//                        return s.getCourses().size() < 3;
//                    } ));

//        forEvery(filter(roster, /*(*//*Student */s/*)*/ -> {
//            for (int i = 0; i < 2; i++) System.out.println("i is " + i);
//                        return s.getCourses().size() < 3;
//                    } ));

//        forEvery(filter(roster, s -> /*{*/
//                        /*return*/ s.getCourses().size() < 3/*;*/
//                    /*}*/ ));

        StudentCriterion sc;
        sc = s -> s.getCourses().size() < 3;

        showAll(getStudentsByCriterion(roster,  sc));

        System.out.println("type of sc is " + sc.getClass().getName());
        Method[] ma = sc.getClass().getMethods();
        for (Method m : ma) {
            System.out.println("> " + m);
        }

//        Object sc2 = s -> s.getCourses().size() < 3;

//        boolean enthu = ((Silly)(s -> s.getCourses().size() < 3)).odd(Student.ofNameGpaCourses("Alan", 2.1));
    }
}
