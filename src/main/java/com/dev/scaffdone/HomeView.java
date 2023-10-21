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
        VerticalLayout title = createHeader();
        CalculationManager calculationManager = createCalculationManager();

        HorizontalLayout layout = createHorizontalLayout(calculationManager);
        add(
                title,
                grid,
                layout,
                new HorizontalLayout(new AdditionalInfoManager(), calculationManager));
    }

    private static CalculationManager createCalculationManager() {
        CalculationManager calculationManager = new CalculationManager();
        calculationManager.addClassName("home-view-horizontal-layout-1");
        return calculationManager;
    }

    private static VerticalLayout createHeader() {
        H1 header = new H1("Scaffolding Done");
        header.addClassName("home-view-h1-1");
        return new VerticalLayout(header);
    }

    private HorizontalLayout createHorizontalLayout(CalculationManager calculationManager) {
        UserSelectionManager userSelectionManager = new UserSelectionManager(this.service);
        ModulesManager modulesManager = new ModulesManager(calculationManager);
        LengthManager lengthManager = new LengthManager(calculationManager);
        HeightManager heightManager = new HeightManager(calculationManager);

        return new HorizontalLayout(
                userSelectionManager,
                modulesManager,
                lengthManager,
                heightManager);
    }

    private void initExampleData(ScaffoldGrid grid) {
        grid.setItems(
                service.getScaffolds()
        );
    }

}
