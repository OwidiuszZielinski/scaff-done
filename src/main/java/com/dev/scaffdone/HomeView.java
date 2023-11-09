package com.dev.scaffdone;


import com.dev.scaffdone.components.*;
import com.dev.scaffdone.core.scaffolding.ScaffoldingService;
import com.dev.scaffdone.core.scaffolding.dto.ScaffoldingDTO;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import jakarta.annotation.security.PermitAll;

@Route("main")
@PermitAll
@Theme("scaff-done")
public class HomeView extends VerticalLayout implements AppShellConfigurator {
    private final ScaffoldingService service;
    private final ScaffoldGrid grid;
    private final CalculationManager calculationManager;
    private final UserSelectionManager userSelectionManager;
    private final ModulesManager modulesManager;
    private final AdditionalInfoManager additionalInfoManager;

    public HomeView(ScaffoldingService service) {
        this.service = service;
        grid = new ScaffoldGrid();
        initExampleData(grid);
        VerticalLayout title = createHeader();

        calculationManager = createCalculationManager();
        userSelectionManager = new UserSelectionManager(this.service);
        modulesManager = new ModulesManager(calculationManager);
        LengthManager lengthManager = new LengthManager(calculationManager);
        HeightManager heightManager = new HeightManager(calculationManager);
        additionalInfoManager = new AdditionalInfoManager();

        HorizontalLayout layout = new HorizontalLayout(
                userSelectionManager, modulesManager, lengthManager, heightManager);

        calculationManager.getSave().addClickListener(event -> saveScaffolding());
        add(title, grid, layout, new HorizontalLayout(additionalInfoManager, calculationManager));
    }

    private CalculationManager createCalculationManager() {
        final CalculationManager calculationManager;
        calculationManager = new CalculationManager();
        calculationManager.addClassName("home-view-horizontal-layout-1");
        return calculationManager;
    }

    private void saveScaffolding() {
        ScaffoldingDTO scaffoldingDTO = ScaffoldingDTO.builder()
                .modules(modulesManager.getScaffoldingModules())
                .username(userSelectionManager.getUserName())
                .totalLength(calculationManager.getScaffoldingLength())
                .resultSquareMeters(calculationManager.getSquareMeters())
                .height(calculationManager.getScaffoldingHeight())
                .otherInformation(additionalInfoManager.getAdditionalInfo())
                .build();

        service.add(scaffoldingDTO);
        grid.setItems(service.getScaffolds());
    }

    private static VerticalLayout createHeader() {
        H1 header = new H1("Scaffolding Done");
        header.addClassName("home-view-h1-1");
        return new VerticalLayout(header);
    }

    private void initExampleData(ScaffoldGrid grid) {
        grid.setItems(
                service.getScaffolds()
        );
    }

}
