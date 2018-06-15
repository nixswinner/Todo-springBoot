package com.nanocomputing.Todo;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.FontIcon;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class TodoUi extends UI {
    private VerticalLayout layout;
    @Autowired
    TodoLayout todoLayout;
    @Autowired
    TodoRepository todoRepository;
    @Override
    protected void init(VaadinRequest vaadinRequest) {

        setupLayout();
        addHeader();
        addForm();
        addTodoList();
        addActionButton();
    }

    private void setupLayout() {
        layout = new VerticalLayout();
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(layout);

    }

    private void addHeader() {
        Label header = new Label("TODO");
        header.addStyleName(ValoTheme.LABEL_H1);//h1
        header.setSizeUndefined();
        layout.addComponent(header);
    }

    private void addForm() {
        HorizontalLayout formlayout=new HorizontalLayout();
        formlayout.setSpacing(true);//spacing
        formlayout.setWidth("80%");//with
        TextField task = new TextField();
        task.setWidth("100%");
        Button addButton = new Button();
        addButton.setIcon(VaadinIcons.PLUS);
        addButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        //add clicklister to add button

        addButton.addClickListener(click -> {
            if (!task.getValue().isEmpty())
            {
                todoLayout.add(new Todo(task.getValue(),false));
                task.clear();
                task.focus();

            }else
            {
                //notify no task added
                Notification.show("Error",
                        "You must Provide a Todo Task",
                        Notification.TYPE_ERROR_MESSAGE);
            }

        });
        //on enter
        addButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        //text field take all spaces
        formlayout.addComponentsAndExpand(task);
        formlayout.addComponent(addButton);
        formlayout.setExpandRatio(task,1);
        layout.addComponent(formlayout);

    }

    private void addTodoList() {
        todoLayout.setWidth("80%");
        layout.addComponent(todoLayout);

    }

    private void addActionButton() {
        Button deleteCompleted = new Button("Delete Completed");
        deleteCompleted.addClickListener(click->{
            //implementDelete
            todoLayout.deleteCompleted();

            //message box

        });
        layout.addComponent(deleteCompleted);

    }
}
