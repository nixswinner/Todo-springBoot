package com.nanocomputing.Todo.controllers;

import com.nanocomputing.Todo.Todo;
import com.nanocomputing.Todo.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class RestApiController {
    @Autowired
    TodoRepository todoRepository;

    @RequestMapping(value = "alltodos",method = RequestMethod.GET)
    public ResponseEntity<List<Todo>> getAllTodos()
    {

        List<Todo> todos = todoRepository.findAll();
        if (todos.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


            return new ResponseEntity<List<Todo>>(todos,HttpStatus.OK);

    }
}
