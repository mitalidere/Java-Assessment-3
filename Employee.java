import java.util.*;
import java.util.function.Consumer;

public class Employee {
    int id;
    String name;
    double salary;
    String department;
    Employee() {
        id = 0;
        name = null;
        salary = 0;
        department = null;
    }
    Employee(int id, String name, double salary, String department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public static void addEmployees(List<Employee> employees, int id, String name, Double salary, String department) {
        Consumer<Employee> c = employee -> employees.add(employee);
        c.accept(new Employee(id, name, salary, department));
    }
    public static void filterEmployees(List<Employee> employees, Double salary) {
        employees.stream().filter(n->n.salary>salary).forEach(System.out::println);
    }

    public static Optional<Employee> findEmployeeByName(List<Employee> employees, String name) {
        Employee e = employees.stream().filter(n -> n.name.equals(name)).findFirst().orElse(new Employee());
        return Optional.ofNullable(e);
    }

    public static void displayEmployees(List<Employee> employees) {
        employees.stream().map(n->n.name).forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "id: "+ id + ", name: " + name + ", salary: " + salary + ", department: " + department;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();
        while(true) {
            System.out.println("1. Add employees\n2. Filter employees by salary\n3. Display employee names\n4. Find employees by name\n5. Exit");
            try {
                System.out.print("Enter choice: ");
                int input = sc.nextInt();
                switch (input) {
                    case 1:
                        try {
                            System.out.print("Enter employee id: ");
                            int id = sc.nextInt();
                            System.out.print("Enter employee name: ");
                            String name = sc.next();
                            sc.nextLine();
                            System.out.print("Enter salary: ");
                            double salary = sc.nextDouble();
                            sc.nextLine();
                            System.out.print("Enter department: ");
                            String department = sc.next();
                            addEmployees(employees, id, name, salary, department);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input");
                        }
                        break;
                    case 2:
                        System.out.print("Enter salary: ");
                        double salary = sc.nextDouble();
                        filterEmployees(employees, salary);
                        break;
                    case 3:
                        displayEmployees(employees);
                        break;
                    case 4:
                        System.out.print("Enter the name of employee: ");
                        String name = sc.next();
                        Optional<Employee> o = findEmployeeByName(employees, name);
                        System.out.println(o);
                        break;
                    case 5:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Enter valid choice");
                }
            }
            catch(InputMismatchException e) {
                System.out.println("Invalid input");
            }
            System.out.println();
            sc.nextLine();
        }
    }
}
