package ua.slyrack.springcourse.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.slyrack.springcourse.entity.Status;
import ua.slyrack.springcourse.entity.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private Map<Long, Task> tasks = new HashMap<>();

    public Map<Long, Task> getTasks() {
        return tasks;
    }

    @PostMapping
    public boolean createTask(@RequestBody Task task) {
        tasks.put(task.getId(), task);
        return true;
    }

    @GetMapping
    public ArrayList<Task> getAll() {
        return new ArrayList<>(tasks.values());
    }

    @GetMapping("/{id}")
    public Task findById(@PathVariable long id) {
        return tasks.get(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable long id, @RequestBody Task task) {
        Task existingTask = tasks.get(id);
        if (existingTask == null) {
            return ResponseEntity.notFound().build();
        }
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());
        return ResponseEntity.ok(existingTask);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable long id) {
        if (tasks.get(id) == null) {
            return false;
        }
        tasks.remove(id);
        return true;
    }

    @GetMapping("/tasks/search?status=Done")
    public ArrayList<Task> getByStatus(@RequestParam(name = "status") Status status) {
        ArrayList<Task> result = new ArrayList<>();

        if (status != null) {
            for (Task task : tasks.values()) {
                if (task.getStatus() == status) {
                    result.add(task);
                }
            }
        }

        return result;
    }
}
