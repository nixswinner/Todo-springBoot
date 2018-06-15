package com.nanocomputing.Todo;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringComponent
public class TodoLayout extends VerticalLayout {
    @Autowired
    TodoRepository todoRepository;
    @PostConstruct
    void init()
    {
        update();

    }
    //update the ui
    private void update() {
        setTodos(todoRepository.findAll());
    }

    private void setTodos(List<Todo> todos) {
        removeAllComponents();
        todos.forEach(todo -> {
            addComponent(new TodoItemLayout(todo));
        });
    }

    public void add(Todo todo) {
        todoRepository.save(todo);
        update(); //update the UI
    }


    public void deleteCompleted() {
        todoRepository.deleteByDone(true);
        update();
    }
}
