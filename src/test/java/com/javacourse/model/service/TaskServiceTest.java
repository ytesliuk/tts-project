package com.javacourse.model.service;

import com.javacourse.model.entity.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TaskServiceTest {

    @Test
    public void getUsersByNameTest() {
        String name = "step";
        List<User> users = new TaskService().getUsersByName(name, true);
        long number = users.stream().filter((x) -> x.getLastName().toLowerCase().contains(name.toLowerCase())).count();

        assertTrue(number > 0);
        assertEquals(users.size(),number);
    }

    @Test
    public void getUsersByDepartment() {
        User.Department department = User.Department.IT_Support;
        List<User> users = new TaskService().getUsersByDepartment(department);
        long number = users.stream().filter((x) -> x.getDepartment().equals(department)).count();

        assertTrue(number > 0);
        assertEquals(users.size(),number);
    }
}