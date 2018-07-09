package com.universe.exploration.userinterface;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.universe.exploration.UniverseExploration;
import com.universe.exploration.audio.SoundEffect;
import com.universe.exploration.common.CoreConfiguration;
import com.universe.exploration.common.tools.GdxHelper;
import com.universe.exploration.crew.CrewMemberStatus;
import com.universe.exploration.crewmember.CrewMember;
import com.universe.exploration.crewmember.CrewMemberTools;
import com.universe.exploration.listener.UEEvent;
import com.universe.exploration.listener.UEListener;
import com.universe.exploration.localization.Localizer;
import com.universe.exploration.player.CrewStatusManager;
import com.universe.exploration.spritecontainer.PlanetHandler;
import com.universe.exploration.spritecontainer.PlanetSprite;
import com.universe.exploration.starsystem.components.PlanetCelestialComponent;
import com.universe.exploration.survey.Survey;
import com.universe.exploration.userinterface.components.*;
import com.universe.exploration.userinterface.components.hud.TopRightHud;
import com.universe.exploration.userinterface.components.log.LogDisplay;
import com.universe.exploration.userinterface.components.log.LogDisplayTable;
import com.universe.exploration.userinterface.components.survey.SurveyDetailsTable;
import com.universe.exploration.userinterface.components.survey.SurveyTeamSelection;
import com.universe.exploration.userinterface.components.window.BasicWindow;
import com.universe.exploration.userinterface.components.window.WindowType;
import com.universe.exploration.userinterface.data.DataPair;
import com.universe.exploration.userinterface.data.DataPairTableFactory;
import com.universe.exploration.userinterface.data.container.CrewMemberDetails;
import com.universe.exploration.userinterface.data.container.DataPairContainer;
import com.universe.exploration.userinterface.data.container.LeftSideHUD;
import com.universe.exploration.userinterface.forms.FormContainer;
import com.universe.exploration.userinterface.forms.PlanetSurveyForm;
import com.universe.exploration.userinterface.skins.UserInterfaceBank;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * {@link UniverseExploration} and no other class should have an instance of
 * UIController.
 * </p>
 * <p>
 * <p>
 * TODO: {@link UniverseExploration} and {@link UIController} both perform
 * actions that do not belong in these classes. Refactor.
 * </p>
 *
 * @author 25.8.2015 Teemu Puurunen
 */
public class UIController {

    private Stage uiStage;

    private UEListener hyperspaceJumpListener;

    private UEListener planetSurveyListener;

    private LeftSideHUD leftsidePlayerStatus;

    private PlanetSelection planetSelection;

    public WindowContainer windowContainer;

    /**
     * <p>
     * This flag determines is hyperspace jump is allowed. Jump can be disabled
     * for example when game over window is shown.
     * </p>
     */
    private boolean isHyperspaceJumpAllowed = true;

    /**
     * Use to send messages for logging.
     */
    private UEListener logMessageListener;

    /**
     * <p>
     * Visual representation of game log.
     * </p>
     */
    private final LogDisplay logDisplay;

    private static final float RIGHT_SIDE_BUTTON_WIDTH = 500;

    public UIController(PlanetHandler gameViewObjectContainer, List<PlanetCelestialComponent> planetList) {

        planetSelection = new PlanetSelection(gameViewObjectContainer, planetList);

        logDisplay = new LogDisplay(10, UserInterfaceBank.userInterfaceSkin);
        uiStage = new Stage(new ScreenViewport());

        uiStage.addActor(createTopLeftHUD());
        uiStage.addActor(new TopRightHud(uiStage.getWidth(), createHyperspaceJumpButton(), createCrewControlButton(),
                createFollowSurveyButton(), createOptionsButton()));
        uiStage.addActor(createBottomRightHUDTable());
        uiStage.addActor(new LogDisplayTable(uiStage.getWidth(), logDisplay.getLogDisplayTable()));
        windowContainerSetup();
    }


    private void windowContainerSetup() {
        windowContainer = new WindowContainer();
        windowContainer.setWindowsThatMustAlert(WindowType.PLANET_DETAILS, WindowType.SURVEY_WINDOW);
        windowContainer.setSpecificedWindowChangeListener(createWindowChangeListener());
    }

    private UEListener createWindowChangeListener() {
        return new UEListener() {
            @Override
            public void handleEventClassEvent(UEEvent e) {
                WindowContainerEvent event = (WindowContainerEvent) e.getPayLoad();
                UniverseExploration.gameStatus.activateSurveyMode(event.equals(WindowContainerEvent.ADD));
            }
        };
    }

    /**
     * @return the uiStage
     */
    public Stage getUiStage() {
        return uiStage;
    }

    /**
     * Update UI
     */
    public void updateUI(CrewStatusManager ps) {
        updatePlayerStatusToUI(ps);

        if ((int) ps.getPower() == 0) {
            isHyperspaceJumpAllowed = false;
        }

        planetSelection.disablePlanetSelection(UniverseExploration.gameStatus.isZoomIn());
        uiStage.act(GdxHelper.getDeltaTime());
        uiStage.draw();
    }

    public VerticalGroup createTopLeftHUD() {
        leftsidePlayerStatus = new LeftSideHUD();
        leftsidePlayerStatus.createPairs();

        VerticalGroup table = new VerticalGroup();
        table.padTop(30);
        table.padLeft(30);
        table.align(Align.left | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());
        table.addActor(populateWithStatus(leftsidePlayerStatus));

        table.addActor(planetSelection.createPlanetSelectionTable());

        return table;
    }

    public void createPlanetClickWindow(final UEEvent e) {
        openWithPrimaryButtonAction(WindowType.PLANET_DETAILS,
                new DataPairTableFactory().createPlanetInformationTable((PlanetSprite) e.getPayLoad()),
                createSurveyTeamSelectionClickListener(e));
    }

    private ClickListener createSurveyTeamSelectionClickListener(final UEEvent e) {

        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                windowContainer.closeWindow(WindowType.PLANET_DETAILS);
                createSurveyTeamSelectionWindow((PlanetSprite) e.getPayLoad());
            }
        };
    }

    private Table populateWithStatus(DataPairContainer dataPairContainer) {
        Table tableRepresentation = new Table();
        tableRepresentation.align(Align.left | Align.top);

        for (DataPair dataPair : dataPairContainer.getPairList()) {
            tableRepresentation.add(dataPair.getLabel()).left();
            tableRepresentation.add(dataPair.getValue()).left();
            tableRepresentation.row();
        }

        return tableRepresentation;
    }

    public void updateLog(LinkedList<String> logItems) {
        logDisplay.updateValuesToTable(logItems);
    }

    /**
     * Create HUD bottom. Add all the buttons and their actions.
     *
     * @return
     */
    public VerticalGroup createBottomRightHUDTable() {
        VerticalGroup table = new VerticalGroup();
        table.setWidth(uiStage.getWidth());
        table.align(Align.right | Align.bottom);
        table.setPosition(0, 100);
        table.padTop(30);
        table.padRight(30);

        if (!Gdx.app.getType().equals(ApplicationType.WebGL)) {
            table.addActor(createQuitButton());
        }

        return table;
    }


    /**
     * Create hyperspace jump button
     *
     * @return
     */
    public TextButton createHyperspaceJumpButton() {
        TextButton button = new ButtonFactory().createTextButton(Localizer.getInstance().get("BTN_HYPERSPACE_JUMP"),
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if (!UniverseExploration.gameStatus.isPaused() && isHyperspaceJumpAllowed) {
                            UniverseExploration.audioManager.playSoundEffect(SoundEffect.HYPERSPACEJUMP);
                            final UEDialog dialog = new UEDialog(Localizer.getInstance().get("DESC_HYPERSPACE_JUMP"));
                            dialog.show(uiStage);
                            Timer.schedule(new Timer.Task() {
                                @Override
                                public void run() {
                                    dialog.hide();
                                    hyperspaceJumpListener.handleEventClassEvent();
                                }
                            }, 1);
                        }
                    }
                });

        button.setWidth(RIGHT_SIDE_BUTTON_WIDTH);

        return button;
    }

    /**
     * Create hyperspace jump button
     *
     * @return
     */
    public TextButton createCrewControlButton() {
        TextButton button = new ButtonFactory().createTextButton(Localizer.getInstance().get("BTN_CREW_CONTROL"),
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        openWithoutPrimaryButtonAction(WindowType.CREW_MANAGEMENT, createCrewTable());
                    }
                });

        button.setWidth(RIGHT_SIDE_BUTTON_WIDTH);
        return button;
    }

    public TextButton createOptionsButton() {
        TextButton button = new ButtonFactory().createTextButton(Localizer.getInstance().get("BTN_OPTIONS"),
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        show(new Options().createOptionsWindow(windowContainer));
                    }
                });

        button.setWidth(RIGHT_SIDE_BUTTON_WIDTH);

        return button;
    }

    public TextButton createFollowSurveyButton() {
        TextButton button = new ButtonFactory().createTextButton(Localizer.getInstance().get("BTN_FOLLOW_SURVEY"),
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        openWithoutPrimaryButtonAction(WindowType.SURVEY_MANAGEMENT, createSurveyTable());
                    }
                });

        button.setWidth(RIGHT_SIDE_BUTTON_WIDTH);
        return button;
    }

    public void createGameOverWindow(ClickListener tryAgainAction) {
        show(windowContainer.getWindowFactory().createWindowWithSecondaryAction(WindowType.GAME_OVER, null,
                "BTN_QUIT_GAME", tryAgainAction, createQuitClickListener()));
    }

    public void createQuitDialog() {
        show(windowContainer.getWindowFactory().createWindow(WindowType.QUIT_WINDOW, null, createQuitClickListener()));
    }

    private ClickListener createQuitClickListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!Gdx.app.getType().equals(ApplicationType.WebGL)) {
                    Gdx.app.exit();
                }
            }
        };
    }

    /**
     * @param window
     * @param <T>
     */
    public <T extends Actor> void show(BasicWindow window) {
        if (!UniverseExploration.gameStatus.isPaused()) {
            windowContainer.add(window.getWindowType(), window);
            uiStage.addActor(window);
        }
    }

    /**
     * Create quit button.
     *
     * @return
     */
    private TextButton createQuitButton() {
        return new ButtonFactory().createTextButton(Localizer.getInstance().get("BTN_QUIT_GAME"), new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                createQuitDialog();
            }
        });
    }

    private void fireLogMessageListener(String message) {
        logMessageListener.handleEventClassEvent(new UEEvent(message));
    }

    /**
     * Create a window where the results of the survey are summarized.
     *
     * @param surveydata
     */
    public void createSurveyClosedWindow(List<String> surveydata) {
        Table table = new Table(UserInterfaceBank.userInterfaceSkin);

        for (String row : surveydata) {
            table.add(row);
            table.row();
        }

        BasicWindow surveyClosedWindow = windowContainer.getWindowFactory().createWindow(WindowType.SURVEY_CLOSED,
                table, new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        windowContainer.closeWindow(WindowType.SURVEY_CLOSED);
                    }
                });

        show(surveyClosedWindow);
    }

    /**
     * Open window without primary button action. OK action just closes the
     * window.
     *
     * @param type
     * @param table
     * @param okAction
     */
    public void openWithPrimaryButtonAction(WindowType type, UETable table, ClickListener okAction) {
        show(windowContainer.getWindowFactory().createWindow(type, table, okAction));
    }

    /**
     * Open window without primary button action. OK action just closes the
     * window.
     *
     * @param type
     * @param table
     */
    public void openWithoutPrimaryButtonAction(WindowType type, UETable table) {
        show(windowContainer.getWindowFactory().createWindow(type, table, null));
    }

    /**
     * Creates a table containing a list of surveys. TODO: move to some help
     * class.
     *
     * @return {@link UETable} containing surveys.
     */
    private UETable createSurveyTable() {
        UETable table = new UETable();

        for (Survey survey : UniverseExploration.gameStatus.getSurveyStatusContainer()) {
            Table cell = new Table();
            cell.padBottom(15);
            cell.padRight(15);
            cell.add(new UELabel(Localizer.getInstance().get("LABEL_NAME") + survey.getSurveyName()));
            cell.row();
            cell.add(new UELabel(new CrewMemberTools().concatenateCrewMemberListNames((survey.getSurveyTeam()))));
            cell.row();
            cell.add(new ButtonFactory().createTextButton(Localizer.getInstance().get("LABEL_SHOW_DETAILS"),
                    createShowSurveyDetailsClickListener(survey)));
            table.add(cell);

        }

        return table;
    }

    /**
     * Create a table that shows all of the crew members.
     *
     * @return
     */
    private UETable createCrewTable() {
        UETable table = new UETable();

        int x = 0;

        for (CrewMember crewmember : UniverseExploration.gameStatus.getCrew().getCrewmen()) {
            x++;
            Table cell = new Table();
            cell.padBottom(15);
            cell.padRight(15);
            cell.add(new Label(crewmember.getName(), UserInterfaceBank.userInterfaceSkin));
            cell.row();
            cell.add(new Label(Localizer.getInstance().get(crewmember.getStatus()),
                    UserInterfaceBank.userInterfaceSkin));
            cell.row();
            cell.add(new ButtonFactory().createTextButton(Localizer.getInstance().get("LABEL_SHOW_DETAILS"),
                    createCrewmemberDetailsClickListerener(crewmember)));
            table.add(cell);

            // 5 per row seems good.
            if (x == 5) {
                table.row();
            }
        }

        return table;
    }

    private ClickListener createShowSurveyDetailsClickListener(final Survey survey) {
        return new ClickListener() {
            /*
             * (non-Javadoc)
             *
             * @see
             * com.badlogic.gdx.scenes.scene2d.utils.ClickListener#clicked(com.
             * badlogic.gdx.scenes.scene2d.InputEvent, float, float)
             */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                BasicWindow window = windowContainer.getWindowFactory().createWindow(WindowType.SURVEY_DETAILS,
                        new SurveyDetailsTable(survey), null);

                show(window);
            }
        };
    }

    private ClickListener createCrewmemberDetailsClickListerener(final CrewMember crewMember) {
        return new ClickListener() {
            /*
             * (non-Javadoc)
             *
             * @see
             * com.badlogic.gdx.scenes.scene2d.utils.ClickListener#clicked(com
             * .badlogic.gdx.scenes.scene2d.InputEvent, float, float)
             */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                final CrewMemberDetails crewMemberDetails = new CrewMemberDetails(crewMember);
                crewMemberDetails.createPairs();

                BasicWindow window = windowContainer.getWindowFactory().createWindow(WindowType.CREWMEMBER_DETAILS,
                        createCrewMemberDetailsPane(crewMemberDetails), null);

                show(window);
            }
        };
    }

    private ScrollPane createCrewMemberDetailsPane(CrewMemberDetails details) {
        VerticalGroup group = new VerticalGroup();
        group.addActor(populateWithStatus(details));
        group.addActor(UIComponentFactory.createSpacer());
        group.addActor(new ButtonFactory().createTextButton(Localizer.getInstance().get("BTN_KICK_OUT_OF_AIRLOCK"),
                createaKillCrewManClickListener(details.getCrewMember())));
        group.addActor(UIComponentFactory.createSpacer());

        return new ScrollPane(group);
    }

    private ClickListener createaKillCrewManClickListener(final CrewMember crewmember) {
        return new ClickListener() {
            /*
             * (non-Javadoc)
             *
             * @see
             * com.badlogic.gdx.scenes.scene2d.utils.ClickListener#clicked(com
             * .badlogic.gdx.scenes.scene2d.InputEvent, float, float)
             */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                CrewMemberStatus status = crewmember.getStatus();
                if (status == CrewMemberStatus.ALIVE) {
                    crewmember.setStatus(CrewMemberStatus.KIA);
                    fireLogMessageListener("You tossed " + crewmember.getName() + " out of the airlock!");
                } else {
                    fireLogMessageListener("Cannot throw " + crewmember.getName()
                            + " out of the airlock! This person is " + Localizer.getInstance().get(status));

                }

                windowContainer.closeWindow(WindowType.CREWMEMBER_DETAILS);
                windowContainer.closeWindow(WindowType.CREW_MANAGEMENT);
                // TODO: work out a way to refresh crew management window
                openWithoutPrimaryButtonAction(WindowType.CREW_MANAGEMENT, createCrewTable());
            }
        };
    }

    /**
     * This window allows you to select your crew setup for surveys.
     *
     * @param planetSpriteContainer We pass the {@link PlanetSprite} in order to show a picture of
     *                              the planet as well. TODO: implement above
     * @return
     */
    public void createSurveyTeamSelectionWindow(PlanetSprite planetSpriteContainer) {
        UETable planetInformationTable = new UETable();
        SurveyTeamSelection teamSelection = new SurveyTeamSelection(UniverseExploration.gameStatus.getCrew());
        planetInformationTable.add(teamSelection.createSurveyTeamSelectionTable());
        planetInformationTable.row();

        TableFormContainerPair pair = UIComponentFactory.createHorizontalSlider(0, CoreConfiguration.MAX_DAYS_ON_SURVEY,
                1);
        final PlanetSurveyForm form = (PlanetSurveyForm) pair.getFormContainer();

        final UELabel label = new UELabel(Localizer.getInstance().get("LABEL_SURVEY_LENGTH") + " ("
                + form.getSurveyLength().getValue() + ") days");
        planetInformationTable.add(label);
        planetInformationTable.row();

        form.setPlanet((PlanetCelestialComponent) planetSpriteContainer.getComponentType());
        form.setSelectedCrewMembers(teamSelection.getSelectedCrewMembers());
        form.setSurveyName(teamSelection.getSurveyNameField());
        planetInformationTable.add(pair.getTable());
        planetInformationTable.row();

        form.getSurveyLength().addListener((Event event) -> {
            label.setText(Localizer.getInstance().get("LABEL_SURVEY_LENGTH") + " ("
                    + form.getSurveyLength().getValue() + ") days");
            return true;
        });

        openWithPrimaryButtonAction(WindowType.SURVEY_WINDOW, planetInformationTable,
                createdPlanetSurveyTeamDispatchedClickListener(form));
    }

    private ClickListener createdPlanetSurveyTeamDispatchedClickListener(final PlanetSurveyForm planetSurveyForm) {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                firePlanetSurveyListener(planetSurveyForm);
            }
        };
    }

    private void firePlanetSurveyListener(FormContainer formContainer) {
        if (planetSurveyListener != null) {
            planetSurveyListener.handleEventClassEvent(new UEEvent(formContainer));
        }
    }

    private void updatePlayerStatusToUI(CrewStatusManager playerStatus) {
        leftsidePlayerStatus.updateHUDValues(playerStatus);
    }

    /**
     * @param hyperspaceJumpListener the hyperspaceJumpListener to set
     */
    public void setHyperspaceJumpListener(UEListener hyperspaceJumpListener) {
        this.hyperspaceJumpListener = hyperspaceJumpListener;
    }

    /**
     * @param planetSurveyListener the planetSurveyListener to set
     */
    public void setStartPlanetSurveyListener(UEListener planetSurveyListener) {
        this.planetSurveyListener = planetSurveyListener;
    }

    /**
     * @param planetClickListener the planetClickListener to set
     */
    public void setPlanetClickListener(UEListener planetClickListener) {
        planetSelection.setPlanetClickListener(planetClickListener);
    }

    /**
     * @param selectedPlanetChangedListener the selectedPlanetChangedListener to set
     */
    public void setSelectedPlanetChangedListener(UEListener selectedPlanetChangedListener) {
        planetSelection.setSelectedPlanetChangedListener(selectedPlanetChangedListener);
    }

    /**
     * @param logMessageListener the logMessageListener to set
     */
    public void setLogMessageListener(UEListener logMessageListener) {
        this.logMessageListener = logMessageListener;
    }

    /**
     * @return the windowContainer
     */
    public WindowContainer getWindowContainer() {
        return windowContainer;
    }
}
