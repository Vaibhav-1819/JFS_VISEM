package com.skillnext2;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            // 🔒 Input safety
            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number!");
                sc.next(); // discard invalid input
                continue;
            }

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent(sc);
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    updateStudent(sc);
                    break;
                case 4:
                    deleteStudent(sc);
                    break;
                case 5:
                    running = false;
                    HibernateUtil.shutdown();
                    System.out.println("Application closed.");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        }
        sc.close();
    }

    /* ================= CREATE ================= */
    private static void addStudent(Scanner sc) {
        sc.nextLine(); // clear buffer

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter semester: ");
        int sem = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter department: ");
        String department = sc.nextLine();

        Student student = new Student(name, sem, department);

        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(student);
            tx.commit();
            System.out.println("Student added successfully!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    /* ================= READ ================= */
    private static void viewStudents() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            List<Student> students =
                    session.createQuery("FROM Student ORDER BY id", Student.class).list();

            if (students.isEmpty()) {
                System.out.println("No students found.");
            } else {
                students.forEach(s ->
                        System.out.println(
                                s.getId() + " | " +
                                s.getName() + " | " +
                                s.getSem() + " | " +
                                s.getDepartment()
                        )
                );
            }
        }
    }

    /* ================= UPDATE ================= */
    private static void updateStudent(Scanner sc) {
        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Student student = session.get(Student.class, id);
            if (student == null) {
                System.out.println("Student not found!");
                tx.rollback(); // ✅ FIXED
                return;
            }

            System.out.print("Enter new name: ");
            student.setName(sc.nextLine());

            System.out.print("Enter new semester: ");
            student.setSem(sc.nextInt());
            sc.nextLine();

            System.out.print("Enter new department: ");
            student.setDepartment(sc.nextLine());

            session.merge(student);
            tx.commit();
            System.out.println("Student updated successfully!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    /* ================= DELETE ================= */
    private static void deleteStudent(Scanner sc) {
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();

        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Student student = session.get(Student.class, id);
            if (student != null) {
                session.remove(student);
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found!");
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
