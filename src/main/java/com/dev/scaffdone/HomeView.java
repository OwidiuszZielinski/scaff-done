package com.dev.scaffdone;


import com.dev.scaffdone.components.*;
import com.dev.scaffdone.scaffold.ScaffoldService;
import com.dev.scaffdone.scaffold.entity.ScaffoldModule;
import com.dev.scaffdone.scaffold.entity.Scaffold;
import com.dev.scaffdone.scaffold.entity.Dimension;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import jakarta.annotation.security.RolesAllowed;

import java.util.List;

@Route("main")
@RolesAllowed("ADMIN")
@Theme("scaff-done")
public class HomeView extends VerticalLayout implements AppShellConfigurator {


    public HomeView(ScaffoldService service) {
        ScaffoldGrid grid = new ScaffoldGrid();
        initExampleData(service, grid);
        H1 header = new H1("Scaffold Done");
        header.addClassName("home-view-h1-1");
        VerticalLayout title = new VerticalLayout(header);
        add(
                title,
                grid,
                new HorizontalLayout(new UserSelectionManager(),
                        new DimensionQuantityManager(),
                        new FrameDimensionManager(),
                        new HeightManager()),
                        new OtherInformationManager(),
                        calculation()

        );
    }

    private static void initExampleData(ScaffoldService service, ScaffoldGrid grid) {
        final Scaffold owi = Scaffold.builder().id(1L).modules(List.of(new ScaffoldModule(Dimension.SIZE_073.getSize(), 5)))
                .done(true)
                .otherInformation("21JEDSAIODKJASKLDJSAKLDJASdasddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddKLDJKLSAJDSADJALSDK")
                .username("Owi").height(10.0f).build();

        final Scaffold owi2 = Scaffold.builder().id(1L).modules(List.of(new ScaffoldModule(Dimension.SIZE_073.getSize(), 5)))
                .done(true)
                .otherInformation("21JEDSAIODKJASKLDJSAKLDJASdasddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddKLDJKLSAJDSADJALSDK")
                .username("Owi").height(10.0f).build();

        grid.setItems(
                service.add(owi),
                service.add(owi2)
        );
    }

    private static HorizontalLayout calculation() {
        TextField sizeTextField = new TextField("Enter size");
        TextField sizeLabel = new TextField("Current size is: ");

        // Set ValueChangeMode to EAGER to get real-time updates
        sizeTextField.setValueChangeMode(ValueChangeMode.EAGER);

        // Add a value change listener
        sizeTextField.addValueChangeListener(event -> {
            // sizeLabel.setValue(String.valueOf(currentCalculation));  // Update the Label
        });
        return new HorizontalLayout(sizeTextField, sizeLabel);
    }

}
