package sets;

import entities.Employee;
import entities.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EmployeeList {
    private static List<Employee> employees;

    public EmployeeList(){
        List<Employee> employees = new ArrayList<Employee>();
    }
    public EmployeeList getEmployeeList() {
        return this;
    }
    public void addEmployee(Employee employee) {
        employees.add(employee);
        employees.sort(Comparator.comparing(Person::getName));
    }
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }
    public static boolean checkIdExists(int id) {
        for (Employee employee : employees) {
            if (employee.getIdAuth() == id) {
                return true;
            }
        }
        return false;
    }
    public String toString(){
        String list = "";
        for (Employee employee : employees){
            list += employee.toString();
        }
        return list;
    }
}
