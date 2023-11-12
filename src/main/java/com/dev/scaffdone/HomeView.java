package com.dev.scaffdone;


import com.dev.scaffdone.components.*;
import com.dev.scaffdone.config.SecurityService;
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

    private final SecurityService securityService;
    private final ScaffoldingService service;

    private final ScaffoldGrid grid;
    private final CalculationManager calculationManager;
    private final UserSelectionManager userSelectionManager;
    private final ModulesManager modulesManager;
    private final AdditionalInfoManager additionalInfoManager;






    public HomeView(SecurityService securityService, ScaffoldingService service) {
        this.securityService = securityService;
        this.service = service;

        grid = createGrid();
        HorizontalLayout title = createHeader();
        calculationManager = createCalculationManager();

        userSelectionManager = new UserSelectionManager(this.service);
        modulesManager = new ModulesManager(calculationManager);
        LengthManager lengthManager = new LengthManager(calculationManager);
        HeightManager heightManager = new HeightManager(calculationManager);
        additionalInfoManager = new AdditionalInfoManager();
        calculationManager.getSave().addClickListener(event -> saveScaffolding());


        HorizontalLayout layout = new HorizontalLayout(
                userSelectionManager, modulesManager, lengthManager, heightManager);

        add(title, grid, layout,
                new HorizontalLayout(additionalInfoManager, calculationManager));

    }

    private ScaffoldGrid createGrid() {
        final ScaffoldGrid grid;
        grid = new ScaffoldGrid();
        initExampleData(grid);
        return grid;
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


    private  HorizontalLayout createHeader() {
        H1 header = new H1("Scaffolding Done");
        header.setWidth("600px");
        header.addClassName("home-view-h1-1");
        HorizontalLayout layout = new HorizontalLayout(header);
        layout.setSizeFull();
        GreenButton logout = new GreenButton("Logout");
        HorizontalLayout buLay = new HorizontalLayout(logout);
        buLay.setSizeFull();
        buLay.setJustifyContentMode(JustifyContentMode.END);
        layout.add(buLay);


        logout.addClickListener(event->{
            securityService.logout();

        });
        return layout;
    }

    private void initExampleData(ScaffoldGrid grid) {

        grid.setItems(
                service.getScaffolds()
        );
    }



}
