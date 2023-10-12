package com.dev.scaffdone;


import com.dev.scaffdone.components.*;
import com.dev.scaffdone.core.scaffolding.ScaffoldingService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.Theme;
import jakarta.annotation.security.RolesAllowed;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Route("main")
@RolesAllowed({ "ADMIN", "USER" })
@Theme("scaff-done")
public class HomeView extends VerticalLayout implements AppShellConfigurator {
    private final ScaffoldingService service;

    public HomeView(ScaffoldingService service) {
        this.service = service;
        ScaffoldGrid grid = new ScaffoldGrid();
        initExampleData(grid);
        H1 header = new H1("Scaffold Done");
        header.addClassName("home-view-h1-1");
        VerticalLayout title = new VerticalLayout(header);
        UserSelectionManager userSelectionManager = new UserSelectionManager(this.service);
        DimensionQuantityManager dimensionQuantityManager = new DimensionQuantityManager();

        add(
                title,
                grid,
                new HorizontalLayout(
                        userSelectionManager,
                        dimensionQuantityManager,
                        new FrameDimensionManager(),
                        new HeightManager()),
                new OtherInformationManager(),
                calculation()

        );
    }

    private void initExampleData(ScaffoldGrid grid) {
        grid.setItems(
                service.getScaffolds()
        );
    }

    private static HorizontalLayout calculation() {
        TextField sizeTextField = new TextField("Enter size");
        TextField sizeLabel = new TextField("Current size is: ");
        sizeTextField.addValueChangeListener(event -> {
            String currentSize = sizeTextField.getValue(); // Blokujemy strumień, aby uzyskać aktualną wartość
            VaadinSession.getCurrent().lock(); // Blokujemy sesję przed modyfikacją interfejsu użytkownika
            sizeLabel.setValue(currentSize);
            VaadinSession.getCurrent().unlock(); // Odblokowujemy sesję
        });

        return new HorizontalLayout(sizeTextField, sizeLabel);
    }

    private static Flux<Long> getData(Float currentCalculation) {
        return Flux.interval(Duration.ofMillis(50)).map(sequence -> currentCalculation.longValue());
    }


}
