package com.dev.scaffdone;


import com.dev.scaffdone.components.*;
import com.dev.scaffdone.core.scaffolding.ScaffoldingService;
import com.dev.scaffdone.core.scaffolding.dto.ScaffoldingDTO;
import com.dev.scaffdone.core.scaffolding.model.Colors;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.annotation.WebServlet;

@Route("/main")
@Theme("scaff-done")
public class HomeView extends VerticalLayout implements AppShellConfigurator {
    private final ScaffoldingService service;
    private final CalculationManager calculationManager;

    public HomeView(ScaffoldingService service) {
        this.service = service;
        this.calculationManager = createCalculationManager();
        ScaffoldGrid grid = createGrid();
        UserSelectionManager userSelectionManager = new UserSelectionManager(service);
        ModulesManager modulesManager = new ModulesManager(calculationManager);
        LengthManager lengthManager = new LengthManager(calculationManager);
        HeightManager heightManager = new HeightManager(calculationManager);
        AdditionalInfoManager additionalInfoManager = new AdditionalInfoManager();

        Button save = createButton("SAVE CALCULATION");
        save.getStyle().set("margin-left", "6px");
        save.addClickListener(e -> {
            save(buildScaffoldingDTO(
                    modulesManager,
                    calculationManager,
                    userSelectionManager,
                    additionalInfoManager
            ));
            getCalculations(grid);
        });
        add(
                createHeader(),
                grid,
                new HorizontalLayout(
                        userSelectionManager,
                        modulesManager,
                        lengthManager,
                        heightManager),
                new HorizontalLayout(additionalInfoManager, calculationManager, save));
    }

    private ScaffoldGrid createGrid() {
        ScaffoldGrid grid = new ScaffoldGrid();
        getCalculations(grid);
        return grid;
    }

    private static Button createButton(String text) {
        Button save = new Button(text);
        setGreenButton(save);
        return save;
    }

    private static void setGreenButton(Button button) {
        button.getStyle().set("background-color", Colors.GREEN_COLOR.getHexCode());
        button.getStyle().set("color", "white");
        button.setWidth("190px");
        button.getStyle().set("top", "40px");
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


    private void getCalculations(ScaffoldGrid grid) {
        grid.setItems(
                service.getScaffolds()
        );
    }

    private ScaffoldingDTO buildScaffoldingDTO(ModulesManager modulesManager, CalculationManager calculationManager,
                                               UserSelectionManager userSelectionManager, AdditionalInfoManager additionalInfoManager) {
        return ScaffoldingDTO.builder()
                .modules(modulesManager.getScaffoldingModules())
                .done(true)
                .height(calculationManager.getScaffoldingHeight())
                .username(userSelectionManager.getUserName())
                .totalLength(calculationManager.getScaffoldingLength())
                .resultSquareMeters(calculationManager.getSquareMeters())
                .otherInformation(additionalInfoManager.getAdditionalInfo())
                .build();
    }

    private void save(ScaffoldingDTO scaffoldingDTO) {
        service.add(scaffoldingDTO);
    }

}
