package com.dev.scaffdone;

import com.dev.scaffdone.scaffold.ScaffoldService;
import com.dev.scaffdone.scaffold.entity.ScaffoldData;
import com.dev.scaffdone.scaffold.entity.Size;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("")
public class HomeView extends VerticalLayout {

    public HomeView(ScaffoldService service) {

        Grid<ScaffoldData> grid = createGrid();
        ScaffoldData owi = ScaffoldData.builder().id(1L).sizes(List.of(Size.SIZE_073.getSize(), Size.SIZE_257.getSize()))
                .settled(true)
                .username("Owi").height(10.0f).build();

        grid.setItems(
            service.add(owi)
        );

        add(
                new H1("Scaff-Done"),
                grid
        );

    }

    private static Grid<ScaffoldData> createGrid() {
        Grid<ScaffoldData> grid = new Grid<>(ScaffoldData.class, false);
        grid.addColumn(ScaffoldData::getId).setHeader("ID");
        grid.addColumn(ScaffoldData::getUsername).setHeader("User");
        grid.addColumn(ScaffoldData::getSizes).setHeader("Sizes");
        grid.addColumn(ScaffoldData::getFrameSize).setHeader("Frames");
        grid.addColumn(ScaffoldData::getHeight).setHeader("Height");
        grid.addColumn(ScaffoldData::getTotalLength).setHeader("Total Length");

        grid.addComponentColumn(item -> {
            Button button = new Button();
            if (item.isSettled()) {
                button.setText("YES");
                button.getStyle().set("color", "white");
                button.getStyle().set("background-color", "green");
            } else {
                button.setText("NO");
                button.getStyle().set("background-color", "red");
            }
            button.addClickListener(click -> {
                // Handle button click here
            });
            return button;

        }).setHeader("Settled");
        grid.addColumn(ScaffoldData::getResultSquareMeters).setHeader("Square Meters");
        grid.addComponentColumn(item-> {
            Button delete = new Button(VaadinIcon.TRASH.create());
            delete.getStyle().set("background-color", "red");
            delete.getStyle().set("color", "white");
            delete.addClickListener(e -> {
                // Perform delete operation
                Notification.show("Deleted!");
            });
            return delete;
        });


        return grid;

    }
}
