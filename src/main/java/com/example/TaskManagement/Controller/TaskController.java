package com.example.TaskManagement.Controller;

import com.example.TaskManagement.Entity.Structure;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class TaskController {
    private List<Structure> tasks = new ArrayList<>();

    @GetMapping()
    public List<Structure> getalltasks(){
        return tasks;
    }
    @GetMapping("/{id}")
    public Structure getbyid(@PathVariable Long id){
        for(Structure task : tasks)
        {
            if(task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }
    @PostMapping("/create")
    public Structure createtask(@RequestBody Structure structure){
        try{
            if(structure.getId()==null)
            {
                throw new IllegalArgumentException("Id is Null");
            }
            tasks.add(structure);
            return structure;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping("{id}")
    public Structure updatetask(@PathVariable Long id ,@RequestBody Structure structure)
    {  try {
        for (Structure task : tasks) {
            if (task.getId().equals(id)) {
                task.setTitle(structure.getTitle());
                task.setDescription(structure.getDescription());
                task.setStatus(structure.isStatus());
                return task;
            }
        }
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    return null;
    }
    @DeleteMapping("{id}")
    public String deletetask(@PathVariable Long id)
    {
        tasks.removeIf(task->task.getId().equals(id));
       return "Task with Id" +id +"is Deleted";
    }


}
