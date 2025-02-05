package by.ploskiy.controller;

import by.ploskiy.entitys.BaseRobot;
import by.ploskiy.entitys.Task;
import by.ploskiy.services.LogController;
import by.ploskiy.services.TaskController;
import by.ploskiy.services.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IndexRestControllerTest {

    @Mock
    private TaskController taskController;
    
    @Mock
    private LogController logController;
    
    @Mock
    private TaskService taskService;
    
    @InjectMocks
    private IndexRestController indexRestController;

    @Test
    public void testAddTaskToRobot() {
        // Arrange
        String input = "task=BUILD&name=Robot1";
        Task expectedTask = new Task();
        expectedTask.setTitle("Строить");
        expectedTask.setType("BUILD");
        
        when(taskService.createTaskFromString(input)).thenReturn(expectedTask);
        when(taskService.extractRobotNameFromString(input)).thenReturn("Robot1");

        // Act
        indexRestController.addTaskToRobot(input);

        // Assert
        verify(taskService).createTaskFromString(input);
        verify(taskService).extractRobotNameFromString(input);
        verify(taskController).personalRobotTasr(expectedTask, "Robot1");
    }

    @Test
    public void testControllerTaskList() {
        // Arrange
        Task task = new Task();
        when(taskController.showTaskList()).thenReturn(Arrays.asList(task));

        // Act
        List<Task> result = indexRestController.controllerTaskList();

        // Assert
        assertEquals(1, result.size());
        verify(taskController).showTaskList();
    }

    @Test
    public void testAllTaskDescription() {
        // Act
        Map<String, String> result = indexRestController.allTaskDescription();

        // Assert
        assertEquals(4, result.size());
    }
}
