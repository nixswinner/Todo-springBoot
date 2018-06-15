package com.nanocomputing.Todo;

import com.vaadin.data.Binder;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class TodoItemLayout extends HorizontalLayout {
    private final CheckBox done;
    private final TextField text;

    public TodoItemLayout(Todo todo) {
        done = new CheckBox();
        text = new TextField();
        text.addStyleName(ValoTheme.BUTTON_BORDERLESS);//make borderless

        if (todo.getText().isEmpty())
        {
            Label label = new Label("No task to Todo ");
            label.addStyleName(ValoTheme.LABEL_H1);
            addComponentsAndExpand(label);
        }

        addComponents(done);
        addComponentsAndExpand(text);


        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        //bind the data using binder classses
        Binder<Todo> binder = new Binder<>(Todo.class);
        binder.bindInstanceFields(this); //match fields in this layout and that of the _todo object
        binder.setBean(todo);


    }
}
