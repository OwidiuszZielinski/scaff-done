package com.dev.scaffdone;


import com.dev.scaffdone.components.*;
import com.dev.scaffdone.core.scaffolding.ScaffoldingService;
import com.dev.scaffdone.core.scaffolding.dto.ScaffoldingDTO;
import com.dev.scaffdone.core.scaffolding.model.Dimension;
import com.dev.scaffdone.core.scaffolding.model.Scaffolding;
import com.dev.scaffdone.core.scaffolding.model.ScaffoldingModule;
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
import java.util.List;

@Route("main")
@RolesAllowed({ "ADMIN", "USER" })
@Theme("scaff-done")
public class HomeView extends VerticalLayout implements AppShellConfigurator {



    public HomeView(ScaffoldingService service) {
        Scaffolding owi = Scaffolding.builder().modules(List.of(new ScaffoldingModule(Dimension.SIZE_073.getSize(), 5)))
                .done(true)
                .otherInformation("21JEDSAIODKJASKLDJSAKLDJASdasddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddKLDJKLSAJDSADJALSDK")
                .username("TWOJA STARA").height(10.0f).build();

        Scaffolding owi2 = Scaffolding.builder().modules(List.of(new ScaffoldingModule(Dimension.SIZE_073.getSize(), 5)))
                .done(true)
                .otherInformation("21JEDSAIODKJASKLDJSAKLDJASdasddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddKLDJKLSAJDSADJALSDK")
                .username("Powidiusz").height(10.0f).build();
        service.add(ScaffoldingDTO.from(owi));
        service.add(ScaffoldingDTO.from(owi2));

        ScaffoldGrid grid = new ScaffoldGrid();
        initExampleData(service, grid);
        H1 header = new H1("Scaffold Done");
        header.addClassName("home-view-h1-1");
        VerticalLayout title = new VerticalLayout(header);
        UserSelectionManager userSelectionManager = new UserSelectionManager(service);


        add(
                title,
                grid,
                new HorizontalLayout(
                        userSelectionManager,
                        new DimensionQuantityManager(),
                        new FrameDimensionManager(),
                        new HeightManager()),
                new OtherInformationManager(),
                calculation()

        );
    }

    private static void initExampleData(ScaffoldingService service, ScaffoldGrid grid) {


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
