
package scramble.view.compact;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.awt.Dimension;

import scramble.model.common.impl.PairImpl;
import scramble.model.map.util.LandUtils;
import scramble.model.scores.Scores;
import scramble.utility.Constants;
import scramble.controller.map.MapController;
import scramble.controller.mediator.LogicController;

import javax.swing.Timer;

/**
 * Class that extends javax.swing.JFrame. This class is the main view of the
 * game.
 *
 * @see JFrame
 */
public class GameView extends JFrame {

    /** Width of the window. */
    public static final int WINDOW_WIDTH = 800;
    /** Height of the window. */
    public static final int WINDOW_HEIGHT = LandUtils.multiplyPixelPerSprite(Constants.SPRITE_PER_STAGE_HEIGHT);

    private static final long serialVersionUID = 1L;

    private final JLayeredPane mainPanel;
    private final BackgroundPanel backgroundPanel;
    private final LandscapePanel landscapePanel;
    private final SpaceShipPanel spaceShipPanel;
    private final BulletsPanel bulletsPanel;
    private final RocketPanel rocketPanel;
    private final StartMenu startMenu;
    private final HUDPanel hudPanel;
    private final FuelTankPanel fuelTankPanel;
    private final LogicController logicController;
    private final GameOverPanel gameOverPanel;
    private final Timer repaintTimer;

    /** Constructor of the class GameView. */
    public GameView() {

        this.mainPanel = new JLayeredPane();
        this.mainPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        this.backgroundPanel = new BackgroundPanel();
        this.backgroundPanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.mainPanel.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);

        this.startMenu = new StartMenu();
        this.startMenu.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.mainPanel.add(startMenu, JLayeredPane.PALETTE_LAYER);

        this.landscapePanel = new LandscapePanel();
        this.landscapePanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.landscapePanel.setOpaque(false);

        // SpaceShip panel setup
        this.spaceShipPanel = new SpaceShipPanel();
        this.spaceShipPanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.spaceShipPanel.setOpaque(false);

        // Bullets panel setup
        this.bulletsPanel = new BulletsPanel();
        this.bulletsPanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.bulletsPanel.setOpaque(false);

        // FuelBar panel setup
        this.hudPanel = new HUDPanel();
        this.hudPanel.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.hudPanel.setOpaque(false);

        // Rocket panel setup
        this.rocketPanel = new RocketPanel();
        this.rocketPanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.rocketPanel.setOpaque(false);

        // FuelTank panel setup
        this.fuelTankPanel = new FuelTankPanel(hudPanel.getFuelBar());
        this.fuelTankPanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.fuelTankPanel.setOpaque(false);

        this.logicController = new LogicController(this);

        this.gameOverPanel = new GameOverPanel();
        gameOverPanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.gameOverPanel.setOpaque(false);

        this.repaintTimer = new Timer(32, e -> {

            this.fuelTankPanel.setMapX(this.landscapePanel.getCurrentMapX());
            mainPanel.repaint();
            this.rocketPanel.setMapX(this.landscapePanel.getCurrentMapX());
            this.bulletsPanel.moveBullets();
            if (rocketPanel.isBossOutOfScreen()) {
                showGameOverScreen();
            }
        });

        this.backgroundPanel.startTimer();

    }

    /**
     * Method for the setup of the GameView.
     */
    public void setupGameView() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Scramble 1981");
        this.setResizable(false);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.add(mainPanel);
        this.setVisible(true);
    }

    /**
     * Class constructor for defensive copy.
     *
     * @param view the game view to copy
     */
    public GameView(final GameView view) {

        super();
        this.mainPanel = view.getMainPanel();
        this.backgroundPanel = view.getBackgroundPanel();
        this.startMenu = view.getStartMenu();
        this.landscapePanel = view.getLandscapePanel();
        this.spaceShipPanel = view.getSpaceshipPanel();
        this.bulletsPanel = view.getBulletsPanel();
        this.hudPanel = view.getHudPanel();
        this.rocketPanel = view.getRocketPanel();
        this.fuelTankPanel = view.getFuelTankPanel();
        this.logicController = view.getLogicController();
        this.repaintTimer = view.getRepaintTimer();
        this.gameOverPanel = view.getGameOverPanel();

    }

    /**
     * Getter of the mainPanel.
     *
     * @return the mainpanel of this GameView
     */
    @SuppressFBWarnings
    public JLayeredPane getMainPanel() {
        return this.mainPanel;
    }

    /**
     * Getter of backgroundPanel.
     *
     * @return the background panel of this GameView
     */
    @SuppressFBWarnings
    public BackgroundPanel getBackgroundPanel() {
        return this.backgroundPanel;
    }

    /**
     * Getter of HUDPanel.
     *
     * @return the fuel bar panel of this GameView
     */
    @SuppressFBWarnings
    public HUDPanel getHudPanel() {
        return this.hudPanel;
    }

    /**
     * Getter of FuelTankPanel.
     *
     * @return the tanks panel of this GameView
     */
    @SuppressFBWarnings
    public FuelTankPanel getFuelTankPanel() {
        return this.fuelTankPanel;
    }

    /**
     * Getter of startMenu.
     *
     * @return the startmenu panel of this GameView
     */

    @SuppressFBWarnings
    public StartMenu getStartMenu() {
        return this.startMenu;
    }

    /**
     * Getter of landscapePanel.
     *
     * @return the landscape panel of this GameView
     */

    @SuppressFBWarnings
    public LandscapePanel getLandscapePanel() {
        return this.landscapePanel;
    }

    /**
     * Getter of spaceShipPanel.
     *
     * @return the spaceship panel of this GameView
     */

    @SuppressFBWarnings
    public SpaceShipPanel getSpaceshipPanel() {
        return this.spaceShipPanel;
    }

    /**
     * Getter of bulletsPanel.
     *
     * @return the bullets panel of this GameView
     */

    @SuppressFBWarnings
    public BulletsPanel getBulletsPanel() {
        return this.bulletsPanel;
    }

    /**
     * Getter of RocketPanel.
     *
     * @return the bullets panel of this GameView
     */
    @SuppressFBWarnings
    public RocketPanel getRocketPanel() {
        return this.rocketPanel;
    }

    /**
     * Getter for GameOverPanel.
     * 
     * @return the panel
     */
    @SuppressFBWarnings
    public GameOverPanel getGameOverPanel() {
        return this.gameOverPanel;
    }

    /**
     * Setup of the mainPanel for the start of the game itself.
     */
    public void startGame() {

        this.mainPanel.removeAll();

        this.mainPanel.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        // this.backgroundPanel.startTimer();

        this.mainPanel.add(landscapePanel, JLayeredPane.PALETTE_LAYER);
        // this.landscapePanel.startTimer();

        this.mainPanel.add(spaceShipPanel, JLayeredPane.MODAL_LAYER);
        // this.spaceShipPanel.startTimer();

        this.mainPanel.add(bulletsPanel, JLayeredPane.MODAL_LAYER);
        // this.bulletsPanel.startTimer();

        this.mainPanel.add(rocketPanel, JLayeredPane.MODAL_LAYER);
        // this.rocketPanel.startTimer();

        this.mainPanel.add(fuelTankPanel, JLayeredPane.MODAL_LAYER);
        // this.fuelTankPanel.startTimer();

        this.mainPanel.add(hudPanel, JLayeredPane.POPUP_LAYER);
        // this.hudPanel.startTimer();
        this.hudPanel.resetStage();

        this.startMenu.stopTimer();
        this.startAllPanelTimers();

        // Change the magic number and uncomment below
        // in order to start further on the map then the beginning
        // this.landscapePanel.reset(26800);
        // this.rocketPanel.setMapX(landscapePanel.getCurrentMapX());
        // this.fuelTankPanel.setMapX(landscapePanel.getCurrentMapX());
        // this.rocketPanel.resetRockets();
        // this.fuelTankPanel.resetTanks();

    }

    /** Resets to start menu. */
    public final void setStart() {

        this.stopAllPanelTimers();
        Scores.addScore(Scores.getCurrentScore());
        Scores.resetCurrentScore();

        this.mainPanel.removeAll();

        this.landscapePanel.reset(0);
        this.rocketPanel.setMapX(0);
        this.fuelTankPanel.setMapX(0);
        this.rocketPanel.resetRockets();
        this.fuelTankPanel.resetTanks();
        LogicController.resetLives();

        this.spaceShipPanel.getSpaceship()
                .updatePosition(
                        new PairImpl<>(Constants.SPACESHIP_STARTER_POSITION, Constants.SPACESHIP_STARTER_POSITION));

        this.mainPanel.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        this.mainPanel.add(startMenu, JLayeredPane.PALETTE_LAYER);

        hudPanel.getFuelBar().fillFuel();

        this.startMenu.startTimer();

    }

    /**
     * Resets to the nearest checkpoint.
     *
     * @param restartPos X pos of selected checkpoint
     */
    public void restartFromCheckPoint(final int restartPos) {

        landscapePanel.reset(restartPos);
        spaceShipPanel.getSpaceship()
                .updatePosition(
                        new PairImpl<>(Constants.SPACESHIP_STARTER_POSITION,
                                Constants.SPACESHIP_STARTER_POSITION));
        hudPanel.getFuelBar().fillFuel();
        this.rocketPanel.setMapX(0);
        this.rocketPanel.resetRockets();
        this.fuelTankPanel.setMapX(this.landscapePanel.getCurrentMapX());
        this.fuelTankPanel.resetTanks();

    }

    /**
     * Calculates nearest checkpoint.
     *
     * @return the checkpoint
     */
    public int returnToCheckPoint() {
        final int size = MapController.getStageStartingX().size();
        for (int i = size - 1; i > 1; i--) {
            if (MapController.getStageStartingX().get(i) < this.landscapePanel
                    .getCurrentMapX()) {
                return MapController.getStageStartingX().get(i);
            }
        }
        return MapController.getStageStartingX().get(1);
    }

    /** Stops all the timers of the singular panels inside game view. */
    public final void stopAllPanelTimers() {
        this.landscapePanel.stopTimer();
        this.bulletsPanel.stopTimer();
        this.spaceShipPanel.stopTimer();
        this.hudPanel.stopTimer();
        this.rocketPanel.stopTimer();
        this.fuelTankPanel.stopTimer();
    }

    /** Starts all the timers of the singular panels inside game view. */
    public void startAllPanelTimers() {
        this.landscapePanel.startTimer();
        this.bulletsPanel.startTimer();
        this.spaceShipPanel.startTimer();
        this.hudPanel.startTimer();
        this.rocketPanel.startTimer();
        this.fuelTankPanel.startTimer();
    }

    /** Restarts all the timers of the singular panels inside game view. */
    public void restartAllPanelTimers() {
        this.landscapePanel.restartTimer();
        this.bulletsPanel.restartTimer();
        this.spaceShipPanel.restartTimer();
        this.hudPanel.restartTimer();
        this.rocketPanel.restartTimer();
        this.fuelTankPanel.restartTimer();
    }

    /** Starts repaint timer. */
    public void startRepaintTimer() {
        this.repaintTimer.start();
    }

    private void showGameOverScreen() {
        stopAllPanelTimers(); // Stop all ongoing game processes
        this.mainPanel.removeAll(); // Clear current game view

        // Add the background panel to retain the starry background
        this.mainPanel.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        backgroundPanel.startTimer(); // Continue moving the starry background

        // Add the GameOverPanel on top
        this.mainPanel.add(gameOverPanel, JLayeredPane.PALETTE_LAYER);
        gameOverPanel.enableOverlay();

        this.mainPanel.repaint(); // Ensure the panel is rendered

        final Timer endGameTimer = new Timer(5000, e -> {
            gameOverPanel.disableOverlay();
            setStart();
        });
        endGameTimer.setRepeats(false);
        endGameTimer.start();
    }

    /**
     * Getter for the repaint timer.
     *
     * @return the timer
     */
    private Timer getRepaintTimer() {
        return this.repaintTimer;
    }

    /**
     * Getter for the logic controller implementation.
     *
     * @return the logic controller
     */
    private LogicController getLogicController() {
        return this.logicController;
    }
}
