package com.dev.scaffdone;


import com.dev.scaffdone.components.*;
import com.dev.scaffdone.core.scaffolding.ScaffoldingService;
import com.dev.scaffdone.core.scaffolding.dto.ScaffoldingDTO;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import jakarta.annotation.security.RolesAllowed;

@Route("main")
@RolesAllowed({ "ADMIN", "USER" })
@Theme("scaff-done")
public class HomeView extends VerticalLayout implements AppShellConfigurator {
    private final ScaffoldingService service;

    public HomeView(ScaffoldingService service) {
        this.service = service;
        ScaffoldGrid grid = new ScaffoldGrid();
        CalculationManager calculationManager = createCalculationManager();
        UserSelectionManager userSelectionManager = new UserSelectionManager(this.service);
        ModulesManager modulesManager = new ModulesManager(calculationManager);
        LengthManager lengthManager = new LengthManager(calculationManager);
        HeightManager heightManager = new HeightManager(calculationManager);
        AdditionalInfoManager additionalInfoManager = new AdditionalInfoManager();
        initExampleData(grid);
        VerticalLayout title = createHeader();
        Button button = new Button("TEST SAVE");
        button.addClickListener(e->{
           save(buildScaffoldingDTO(
                   modulesManager,
                   calculationManager,
                   userSelectionManager,
                   additionalInfoManager
           ));
            initExampleData(grid);
        });
        add(
                title,
                grid,
                new HorizontalLayout(
                userSelectionManager,
                modulesManager,
                lengthManager,
                heightManager),
                new HorizontalLayout(additionalInfoManager, calculationManager)
        ,button);
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


    private void initExampleData(ScaffoldGrid grid) {
        grid.setItems(
                service.getScaffolds()
        );
    }

    private ScaffoldingDTO buildScaffoldingDTO(ModulesManager modulesManager, CalculationManager calculationManager,
                                               UserSelectionManager userSelectionManager, AdditionalInfoManager additionalInfoManager){
        return ScaffoldingDTO.builder()
                .modules(modulesManager.getScaffoldingModules())
                .done(true)
                .height(calculationManager.getScaffoldingHeight())
                .username(userSelectionManager.getUserName())
                .totalLength(calculationManager.getScaffoldingLength())
                .resultSquareMeters(calculationManager.getSquareMeters())
                .userDetails(null)
                .otherInformation(additionalInfoManager.getAdditionalInfo())
                .build();
    }
    private void save(ScaffoldingDTO scaffoldingDTO) {
        service.add(scaffoldingDTO);
    }

}
