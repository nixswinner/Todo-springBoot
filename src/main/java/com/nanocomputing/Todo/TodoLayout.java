package com.nanocomputing.Todo;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
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
        if (todos.isEmpty())
        {
            Label label = new Label("Hurray ! No task to Todo");
            label.addStyleName(ValoTheme.LABEL_H2);
            addComponentsAndExpand(label);
        }
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
