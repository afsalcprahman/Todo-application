package com.yutodo.yutodo.services;

import com.yutodo.yutodo.model.TodoItem;
import com.yutodo.yutodo.repository.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TodoService  {

    @Autowired
    TodoRepo repo;

    public List<TodoItem>getAllItem(){
        ArrayList<TodoItem> todoList = new ArrayList<>();
//        repo.findAll().forEach(todoItem -> todoList.add(todoItem));
//        return todoList;
        return repo.findAllByOrderByPriorityAsc();
    }

    public TodoItem getTodoItemByid(Long id){
        return repo.findById(id).get();

    }
    public boolean isComplete(Long id){
       TodoItem todo = getTodoItemByid(id);
       todo.setIsComplete(true);
        return saveOrUpdateToDoItem(todo);
    }

    public boolean saveOrUpdateToDoItem(TodoItem todo){
        TodoItem updateTodo = repo.save(todo);
        if(getTodoItemByid(updateTodo.getId()) != null){
            return true;
        }
        return false;
    }

    public boolean deleteTodoItem(Long id){
        if(getTodoItemByid(id) != null){
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
