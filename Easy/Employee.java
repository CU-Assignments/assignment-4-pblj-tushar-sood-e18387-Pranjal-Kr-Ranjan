package exp4;
import java.util.ArrayList;
import java.util.Scanner;

class Emp {
    int id;
    String name;
    double salary;

    Emp(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public double calculateAnnualSalary() {
        return this.salary * 12;
    }
}

public class Employee {
    private static ArrayList<Emp> employees = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Add Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addEmployee() {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Employee Salary: ");
        double salary = scanner.nextDouble();

        Emp newEmployee = new Emp(id, name, salary);
        employees.add(newEmployee);
        System.out.println("Employee added successfully!");
    }

    private static void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Emp employee : employees) {
                System.out.println("ID: " + employee.id + ", Name: " + employee.name + ", Monthly Salary: " + employee.salary + ", Annual Salary: " + employee.calculateAnnualSalary());
            }
        }
    }
}
