import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainStart extends Application {
	private static final int LOGIN_WINDOW_WIDTH = 500;
	private static final int LOGIN_WINDOW_HEIGHT = 250;
	private static final int CREATE_WINDOW_WIDTH = 300;
	private static final int CREATE_WINDOW_HEIGHT = 200;
	private Level curLvl;
	private Board board;

	private int babyCount;
	private int generalCount;
	private int spawnCount;

	private static final int LEVEL_WINDOW_WIDTH = 800;
	private static final int LEVEL_WINDOW_HEIGHT = 400;

	// The dimensions of the window
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 600;

	// The width and height (in pixels) of each cell that makes up the game.
	private static final int GRID_CELL_WIDTH = 50;
	private static final int GRID_CELL_HEIGHT = 50;

	// The canvas in the GUI. This needs to be a global variable
	// (in this setup) as we need to access it in different methods.
	// We could use FXML to place code in the controller instead.
	private Canvas canvas;

	// Loaded images
	private Image dirtImage;
	private Image bombImage0;
	private Image grassImage;
	private Image tunnelImage;
	private Image deathRatImage;
	private Image babyRatImage;

	private Image bombImage1;
	private Image bombImage2;
	private Image bombImage3;
	private Image bombImage4;
	private Image bombImage5;
	
	private Image stopSignImage1;
	private Image stopSignImage2;
	private Image stopSignImage3;
	private Image stopSignImage4;
	private Image stopSignImage5;
	
	private Image gasImage;

	private Image sterilisationImage;

	private Image poisonImage;
	
	private Image femaleSexChangeImage;
	private Image maleSexChangeImage;
	
	private Image maleRatImage;
	private Image femaleRatImage;
	
	private Image explosionImage;


	// Timeline which will cause tick method to be called periodically.
	private Timeline tickTimeline;

	/**
	 * Setup the new application.
	 * 
	 * @param primaryStage The stage that is to be used for the application.
	 */
	public void start(Stage primaryStage) {
		try {
			Profile.readProfiles();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		
		ArrayList<Profile> profileArray = Profile.getProfilesList();
		
		//Load images
		dirtImage = new Image("Textures_use_PNG\\TILE.png");
		grassImage = new Image("Textures_use_PNG\\GRASS.png");
		tunnelImage = new Image("Textures_use_PNG\\TUNNEL.png");
		
		bombImage0 = new Image("Textures_use_PNG\\EXPLOSION_TS.png");
		bombImage1 = new Image("Textures_use_PNG\\BOMB_1_TS.png");
		bombImage2 = new Image("Textures_use_PNG\\BOMB_2_TS.png");
		bombImage3 = new Image("Textures_use_PNG\\BOMB_3_TS.png");
		bombImage4 = new Image("Textures_use_PNG\\BOMB_4_TS.png");
		bombImage5 = new Image("Textures_use_PNG\\BOMB_5_TS.png");
		
		deathRatImage = new Image("Textures_use_PNG\\DEATH_RAT_TS.png");
		babyRatImage = new Image("Textures_use_PNG\\BABY_RAT_TS.png");
		maleRatImage = new Image("Textures_use_PNG\\MALE_RAT_TS.png");
		femaleRatImage = new Image("Textures_use_PNG\\FEMALE_RAT_TS.png");
		
		stopSignImage1 = new Image("Textures_use_PNG\\STOP_SIGN_1_TS.png");
		stopSignImage2 = new Image("Textures_use_PNG\\STOP_SIGN_2_TS.png");
		stopSignImage3 = new Image("Textures_use_PNG\\STOP_SIGN_3_TS.png");
		stopSignImage4 = new Image("Textures_use_PNG\\STOP_SIGN_4_TS.png");
		stopSignImage5 = new Image("Textures_use_PNG\\STOP_SIGN_5_TS.png");
		
		gasImage = new Image("Textures_use_PNG\\GAS_TS.png");
		
		sterilisationImage = new Image("Textures_use_PNG\\STERILISATION_TS.png");
		
		poisonImage = new Image("Textures_use_PNG\\POISON_TS.png");
		
		maleSexChangeImage = new Image("Textures_use_PNG\\MALE_SEX_CHANGE_TS.png");
		femaleSexChangeImage = new Image("Textures_use_PNG\\FEMALE_SEX_CHANGE_TS.png");
		
		//explosionImage = new Image("Textures_use_PNG\\MALE_SEX_CHANGE_TS.png");

		BorderPane root1 = new BorderPane();

		HBox topBar = new HBox();
		root1.getChildren().add(topBar);

		String messageOfDay = "";
		
		//This gets the message of the day 
		try {
			messageOfDay = MessageOfDay.checkIfSolution();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
		
		//This displays message of the day
		Text mOfD = new Text(messageOfDay);
		mOfD.setWrappingWidth(LOGIN_WINDOW_WIDTH);
		mOfD.setFont(new Font("Ariel", 14));
		topBar.getChildren().add(mOfD);
		
		VBox middleBar = new VBox();
		root1.getChildren().add(middleBar);
		middleBar.setSpacing(5);
		middleBar.setPadding(new Insets(150, 10, 10, 200));

		//Creates a login button
		Button login = new Button();
		login.setText("Login");
		login.setMinWidth(100);
		middleBar.getChildren().add(login);

		//Creates a create account button
		Button createAcc = new Button();
		createAcc.setText("Create Account");
		createAcc.setMinWidth(100);
		middleBar.getChildren().add(createAcc);

		//Creates the text field to add your username
		TextField textField = new TextField();
		textField.setMinWidth(200);
		textField.setMaxWidth(200);
		textField.setText("Ener your username: ");
		root1.setCenter(textField);

		// This sets the first scene which is the login stage
		Scene scene1 = new Scene(root1, LOGIN_WINDOW_WIDTH, LOGIN_WINDOW_HEIGHT);
		primaryStage.setTitle("Login");
		primaryStage.setScene(scene1);
		primaryStage.setResizable(false);
		primaryStage.show();

		//When the login button is pressed
		//it checks with the users we have stored on a file
		//to see if there is a user matching. It it is is pulling their details about 
		//what levels are unlocked.
		login.setOnAction(e -> {
			String user = textField.getText();
			try {
				if(Profile.isCurrentProfile(user)) {
					Profile p = Profile.getProfile(user);
					Boolean[] lvlsUnlocked = p.getLvlsUnlocked();
					BorderPane levelRoot = new BorderPane();

					HBox labelBar = new HBox();
					levelRoot.getChildren().add(labelBar);

					Label userLabel = new Label();
					userLabel.setText("User: " + p.getName());
					userLabel.setMinWidth(LEVEL_WINDOW_WIDTH);
					userLabel.setMinHeight(LEVEL_WINDOW_HEIGHT);
					userLabel.setFont(new Font("Ariel", 18));
					userLabel.setAlignment(Pos.BOTTOM_LEFT);
					labelBar.getChildren().add(userLabel);

					HBox bar = new HBox();
					levelRoot.getChildren().add(bar);
					bar.setSpacing(50);
					bar.setPadding(new Insets(120, 10, 10, 120));
					
					//Creates a level one button that takes you to the first level
					Button lvlOne = new Button();
					lvlOne.setText("Level 1");
					lvlOne.setMinHeight(100);
					lvlOne.setMinWidth(100);
					bar.getChildren().add(lvlOne);

					//Check to see if the level is unlocked for the user
					if(lvlsUnlocked[0]) {
						lvlOne.setDisable(false);
					}else {
						lvlOne.setDisable(true);
					}
					
					//This will take you to the first level of the game
					lvlOne.setOnAction(action -> {
						//This reads the levels in
						try {
							ReadLevelFile.readFiles("C:\\Users\\abhim\\Desktop\\CSE 2021-22\\RATSGN12\\Levels");
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
						
						//This gets the first item in the levels file
						Level lvl = Level.getLevel(0);

						int canvasWidth = lvl.getWidth() * 50;
						int canvasHeigth = lvl.getHeight() * 50;

						//Sets the current level to the one we are on.
						curLvl = lvl;

						Pane root = buildGUI(canvasWidth, canvasHeigth);

						Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

						//Gets the map layout of the level
						char[][] mapLayout = lvl.getMapLayout();

						//Creates a new board with the map layout
						board = new Board(mapLayout);

						//Initialise the counts
						generalCount = 0;
						babyCount = 0;
						spawnCount = 0;
						
						drawGame(curLvl);

						tickTimeline = new Timeline(new KeyFrame(Duration.millis(500), event -> tick()));
						tickTimeline.setCycleCount(Animation.INDEFINITE);

						primaryStage.setScene(scene);
						primaryStage.show();

					});

					Button lvlTwo = new Button();
					lvlTwo.setText("Level 2");
					lvlTwo.setMinHeight(100);
					lvlTwo.setMinWidth(100);
					bar.getChildren().add(lvlTwo);
					
					if(lvlsUnlocked[1]) {
						lvlTwo.setDisable(false);
					}
					else {
						lvlTwo.setDisable(true);
					}
					
					lvlTwo.setOnAction(action -> {
						try {
							ReadLevelFile.readFiles("C:\\Users\\abhim\\Desktop\\CSE 2021-22\\RATSGN12\\Levels");
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
						Level lvl = Level.getLevel(1);
						
						int canvasWidth = lvl.getWidth() * 50;
						int canvasHeigth = lvl.getHeight() * 50;

						curLvl = lvl;

						Pane root = buildGUI(canvasWidth, canvasHeigth);

						Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

						char[][] mapLayout = lvl.getMapLayout();
						board = new Board(mapLayout);

						generalCount = 0;
						babyCount = 0;
						spawnCount = 0;

						drawGame(curLvl);

						tickTimeline = new Timeline(new KeyFrame(Duration.millis(100), event -> tick()));
						tickTimeline.setCycleCount(Animation.INDEFINITE);
						
						primaryStage.setScene(scene);
						primaryStage.show();

					});

					Button lvlThree = new Button();
					lvlThree.setText("Level 3");
					lvlThree.setMinHeight(100);
					lvlThree.setMinWidth(100);
					bar.getChildren().add(lvlThree);

					if(lvlsUnlocked[2]) {
						lvlThree.setDisable(false);
					}
					else {
						lvlThree.setDisable(true);
					}
					
					lvlThree.setOnAction(action -> {					
						try {
							ReadLevelFile.readFiles("C:\\Users\\abhim\\Desktop\\CSE 2021-22\\RATSGN12\\Levels");
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
						Level lvl = Level.getLevel(2);
						int canvasWidth = lvl.getWidth() * 50;
						int canvasHeigth = lvl.getHeight() * 50;

						curLvl = lvl;

						Pane root = buildGUI(canvasWidth, canvasHeigth);

						Scene scene = new Scene(root, 1920, 1080);

						char[][] mapLayout = lvl.getMapLayout();
						board = new Board(mapLayout);

						generalCount = 0;
						babyCount = 0;
						spawnCount = 0;

						drawGame(curLvl);

						tickTimeline = new Timeline(new KeyFrame(Duration.millis(100), event -> tick()));
						tickTimeline.setCycleCount(Animation.INDEFINITE);

						primaryStage.setScene(scene);
						primaryStage.show();

					});

					Button lvlFour = new Button();
					lvlFour.setText("Level 4");
					lvlFour.setMinHeight(100);
					lvlFour.setMinWidth(100);
					bar.getChildren().add(lvlFour);

					lvlFour.setOnAction(action -> {	
						try {
							ReadLevelFile.readFiles("C:\\Users\\abhim\\Desktop\\CSE 2021-22\\RATSGN12\\Levels");
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
						Level lvl = Level.getLevel(3);
						int canvasWidth = lvl.getWidth() * 50;
						int canvasHeigth = lvl.getHeight() * 50;

						curLvl = lvl;

						Pane root = buildGUI(canvasWidth, canvasHeigth);

						Scene scene = new Scene(root, 1920, 1080);

						char[][] mapLayout = lvl.getMapLayout();
						board = new Board(mapLayout);

						generalCount = 0;
						babyCount = 0;
						spawnCount = 0;

						drawGame(curLvl);

						tickTimeline = new Timeline(new KeyFrame(Duration.millis(100), event -> tick()));
						tickTimeline.setCycleCount(Animation.INDEFINITE);

						primaryStage.setScene(scene);
						primaryStage.show();

					});
					
					if(lvlsUnlocked[3]) {
						lvlFour.setDisable(false);
					}
					else {
						lvlFour.setDisable(true);
					}
					
					Scene levelScene = new Scene(levelRoot, LEVEL_WINDOW_WIDTH, LEVEL_WINDOW_HEIGHT);
					primaryStage.setTitle("Select Levels");
					primaryStage.setScene(levelScene);
					primaryStage.centerOnScreen();
					primaryStage.show();
				}
				else {
					System.out.println("Profile does not exist, please create account");
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		});

		//This will take you to a new canvas where you can create a new user
		createAcc.setOnAction(e -> {
			BorderPane createRoot = new BorderPane();

			HBox bar = new HBox();
			createRoot.getChildren().add(bar);

			VBox buttonBar = new VBox();
			createRoot.getChildren().add(buttonBar);
			buttonBar.setSpacing(5);
			buttonBar.setPadding(new Insets(120, 10, 10, 100));

			Button back = new Button();
			back.setText("Back");
			back.setMinWidth(100);
			buttonBar.getChildren().add(back);

			Button create = new Button();
			create.setText("Create Account");
			create.setMinWidth(100);
			buttonBar.getChildren().add(create);

			TextField createAccTextField = new TextField();
			createAccTextField.setMinWidth(200);
			createAccTextField.setMaxWidth(200);
			createAccTextField.setText("Ener your username: ");
			createRoot.setCenter(createAccTextField);

			Scene createScene = new Scene(createRoot, CREATE_WINDOW_WIDTH, CREATE_WINDOW_HEIGHT);
			primaryStage.setTitle("Create User");
			primaryStage.setScene(createScene);
			primaryStage.centerOnScreen();
			primaryStage.show();

			// The back button will take the user back to the login screen
			// where they can then use the account they've created to play the game.
			back.setOnAction(action -> {
				primaryStage.setScene(scene1);
				primaryStage.centerOnScreen();
				primaryStage.show();
			});

			// This will add the username to a file
			create.setOnAction(action -> {
				String user = createAccTextField.getText();
				try {
					if(Profile.isCurrentProfile(user)) {
						System.out.println("Account already created, please login to this one");
					}
					else {
						Profile p = new Profile(user);
						System.out.println("Account created, please go back and login to the account");

					}
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				
			});
		});

	}

	

	/**
	 * Draw the game on the canvas.
	 */
	public void drawGame(Level lvl) {
		//This will get the spawn point location of the baby rats
		BabyRat[][] babyRat = lvl.getSpawnSpots();
		GraphicsContext gc = canvas.getGraphicsContext2D();

		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		gc.setFill(Color.GRAY);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		//Sets the grid height and width for every level
		int gridWidth = (int) (canvas.getWidth() / GRID_CELL_WIDTH);
		int gridHeight = (int) (canvas.getHeight() / GRID_CELL_HEIGHT);

		//This loops through the map and draws the tiles as well as drawing the items and the rats that 
		//are present on every path tile.
		for (int i = 0; i < gridHeight; i++) {
			for (int j = 0; j < gridWidth; j++) {
				if (board.getTile(i, j).getType() == 'G') {
					gc.drawImage(grassImage, j * GRID_CELL_WIDTH, i * GRID_CELL_HEIGHT);
				} else if (board.getTile(i, j).getType() == 'P') {
					gc.drawImage(dirtImage, j * GRID_CELL_WIDTH, i * GRID_CELL_HEIGHT);

					if (board.getTile(i, j).getItem() != null && (board.getTile(i, j).getItem() instanceof StopSign)) {
						gc.drawImage(stopSignImage5, j * GRID_CELL_WIDTH, i * GRID_CELL_HEIGHT);
					} else if(board.getTile(i, j).getItem() != null && (board.getTile(i, j).getItem() instanceof DeathRat)){
						gc.drawImage(deathRatImage, j * GRID_CELL_WIDTH, i * GRID_CELL_HEIGHT);
					} else if(board.getTile(i, j).getItem() != null && (board.getTile(i, j).getItem() instanceof Sterilisation)){
						gc.drawImage(sterilisationImage, j * GRID_CELL_WIDTH, i * GRID_CELL_HEIGHT);
					}else if(board.getTile(i, j).getItem() != null && (board.getTile(i, j).getItem() instanceof Poison)){
						gc.drawImage(poisonImage, j * GRID_CELL_WIDTH, i * GRID_CELL_HEIGHT);
					}else if(board.getTile(i, j).getItem() != null && (board.getTile(i, j).getItem() instanceof Bomb)){
						gc.drawImage(bombImage5, j * GRID_CELL_WIDTH, i * GRID_CELL_HEIGHT);
					}else if(board.getTile(i, j).getItem() != null && (board.getTile(i, j).getItem() instanceof SexChange)){
						gc.drawImage(femaleSexChangeImage, j * GRID_CELL_WIDTH, i * GRID_CELL_HEIGHT);
					}else if(board.getTile(i, j).getItem() != null && (board.getTile(i, j).getItem() instanceof Gas)){
						gc.drawImage(gasImage, j * GRID_CELL_WIDTH, i * GRID_CELL_HEIGHT);
					} else if(board.getTile(i, j).getItem() != null && (board.getTile(i, j).getItem() instanceof Explosion)) {
						gc.drawImage(bombImage0, j * GRID_CELL_WIDTH, i * GRID_CELL_HEIGHT);
					} else if(board.getTile(i, j).getItem() != null && (board.getTile(i, j).getItem() instanceof GasExpand)) {
						gc.drawImage(gasImage, j * GRID_CELL_WIDTH, i * GRID_CELL_HEIGHT);
					} else if(board.getTile(i, j).getItem() != null && (board.getTile(i, j).getItem() instanceof SterilisationExpand)) {
						gc.drawImage(sterilisationImage, j * GRID_CELL_WIDTH, i * GRID_CELL_HEIGHT);
					} else {
						
					}

				} else {
					gc.drawImage(tunnelImage, j * GRID_CELL_WIDTH, i * GRID_CELL_HEIGHT);
				}

				if (board.getTile(i, j).getRat() instanceof BabyRat) {
					gc.drawImage(babyRatImage, j * GRID_CELL_WIDTH, i * GRID_CELL_HEIGHT);

				}
				if (board.getTile(i, j).getRat() instanceof AdultFemaleRat) {
					gc.drawImage(femaleRatImage, j * GRID_CELL_WIDTH, i * GRID_CELL_HEIGHT);

				}
				if (board.getTile(i, j).getRat() instanceof AdultMaleRat) {
					gc.drawImage(maleRatImage, j * GRID_CELL_WIDTH, i * GRID_CELL_HEIGHT);

				}
			}
		}

		//Spawns a new bunch of babies after 150 ticks (15 seconds).
		if (spawnCount % 150 == 0) {
			for (int i2 = 0; i2 < gridHeight; i2++) {
				for (int j2 = 0; j2 < gridWidth; j2++) {
					if (babyRat[i2][j2] != null) {
						board.getTile(i2, j2).addRat(babyRat[i2][j2]);
					}
					if (board.getTile(i2, j2).getRat() instanceof BabyRat) {
						gc.drawImage(babyRatImage, j2 * GRID_CELL_WIDTH, i2 * GRID_CELL_HEIGHT);

					}
				}
			}
		}

	}

	int amountOfRats;
	int score;
	Score sc = new Score(score);
	
	/**
	 * This method is called periodically by the tick timeline
	 * and would for, example move, perform logic in the game,
	 * this might cause the bad guys to move (by e.g., looping
	 * over them all and calling their own tick method).
	 */
	public void tick() {
		// Here we move the player right one cell and teleport

		//We initiate a new array for baby rats, adult rats and items
		//at the start of every tick so that when the rats moved we can then
		//store their new position and get rid of the old one.
		ArrayList<BabyRat> babiesArray = new ArrayList<>();
		ArrayList<Rat> adultsArray = new ArrayList<>();
		ArrayList<Item> itemsArray = new ArrayList<>();
		//This increases the count
		babyCount += 1;
		generalCount += 1;
		spawnCount += 1;
		
		int gridWidth = (int) (canvas.getWidth() / GRID_CELL_WIDTH);
		int gridHeight = (int) (canvas.getHeight() / GRID_CELL_HEIGHT);

		//This is where we loop through the map and see where baby rats/adult rats or items are 
		//and we add them to the array list.
		for (int i = 0; i < gridHeight; i++) {
			for (int j = 0; j < gridWidth; j++) {
				if (board.getTile(i, j).getRat() != null) {

					if (board.getTile(i, j).getRat() instanceof BabyRat) {

						babiesArray.add((BabyRat) board.getTile(i, j).getRat());
						board.getTile(i, j).getRat().initiliaseBoard(board);

					} else if ((board.getTile(i, j).getRat() instanceof AdultFemaleRat ||
							board.getTile(i, j).getRat() instanceof AdultMaleRat)) {

						adultsArray.add(board.getTile(i, j).getRat());
						board.getTile(i, j).getRat().initiliaseBoard(board);

					}
				} else if (board.getTile(i, j).getItem() != null) {
					itemsArray.add(board.getTile(i, j).getItem());
				}
			}
		}

		if (generalCount == 1) {
			amountOfRats = babiesArray.size() + adultsArray.size();
		}
		score = (score + 10 * (amountOfRats - babiesArray.size() + adultsArray.size()));
		sc.setScore(score);
		amountOfRats = babiesArray.size() + adultsArray.size();
		System.out.println(score);
		
		//This makes sure that a baby rat and an item do an action
		//every 2 ticks
		if (babyCount % 2 == 0) {
			for (int i = 0; i < babiesArray.size(); i++) {
				babiesArray.get(i).move();
			}
			
//			for (Item i : itemsArray) {
//				i.tick();
//			}
		}
		
		//This ages up the baby rats
		for (int i = 0; i < babiesArray.size(); i++) {
			babiesArray.get(i).age();
		}

		
		for (int i = 0; i < adultsArray.size(); i++) {
			
		}
		
		for (Item i : itemsArray) {
            if (i instanceof DeathRat) {
                if (babyCount % 2 == 0) {
                    i.tick();
                }
            } else if (i instanceof Bomb || i instanceof Sterilisation) {
                if (generalCount % 10 == 0) { //ticks every second
                    i.tick();
                }
            } else {
                if (generalCount % 5 == 0) {
                    i.tick();
                }
            } 
        }
		
		
		//This makes sure that an adult rat and an item do an action
		//every 5 ticks
		if (generalCount % 5 == 0) {
			for (int i = 0; i < adultsArray.size(); i++) {
				adultsArray.get(i).move();
			}
//			for (Item i : itemsArray) {
//				i.tick();
//			}
		}
		
		//This checks to see if the female rat is pregnant and it gives birth to the babies
		for (int i = 0; i < adultsArray.size(); i++) {
			if (adultsArray.get(i) instanceof AdultFemaleRat) {
				if (((AdultFemaleRat) adultsArray.get(i)).isPregnant()) {
					((AdultFemaleRat) adultsArray.get(i)).pregnantAge();
				}
			}
		}

		//This is the winning and losing check
		if((adultsArray.size() + babiesArray.size()) == 0) {
			System.out.println("Win");
			//(do the highscore here)
		} else {
			if((adultsArray.size() + babiesArray.size()) >= curLvl.getLossCondition() || generalCount == 600) {
				System.out.println("Game Over");
			} else {
				drawGame(curLvl);
			}
		}
		
	}

	/**
	 * React when an object is dragged onto the canvas.
	 * 
	 * @param event The drag event itself which contains data about the drag that
	 *              occurred.
	 */
	public void canvasDragDroppedOccured(DragEvent event) {
		double x = event.getX();
		double y = event.getY();

		//This will make sure that an item will get placed on the middle of a tile 
		double tempX = x / 50;
		x = Math.floor(tempX) * 50.0;

		double tempY = y / 50;
		y = Math.floor(tempY) * 50.0;

		int itemX = (int) x / 50;
		int itemY = (int) y / 50;

		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		//now here if is instance of something draw that 
		gc.drawImage(bombImage5, x, y);

		
		//These all work they just don't work at the same time
		//Please uncomment and check each item individually.
		//Also, some might only work on the corners or junctions.
		
		//Bomb item = new Bomb(board, board.getTile(itemY, itemX));
		//StopSign item = new StopSign(board, board.getTile(itemY, itemX));
		DeathRat item = new DeathRat(board, board.getTile(itemY, itemX));
		//Poison item = new Poison(board, board.getTile(itemY, itemX));
		//Sterilisation item = new Sterilisation(board, board.getTile(itemY, itemX));
		//Gas item = new Gas(board, board.getTile(itemY, itemX));
		//SexChange item = new SexChange(board, board.getTile(itemY, itemX), 1);
		//SexChange item = new SexChange(board, board.getTile(itemY, itemX), 2);

		
		//This adds the item to the board
		board.getTile(itemY, itemX).addItem(item);
		
		
		

	}

	/**
	 * Create the GUI.
	 * 
	 * @return The panel that contains the created GUI.
	 */
	private Pane buildGUI(int canvasWidth, int canvasHeigth) {
		BorderPane root = new BorderPane();

		canvas = new Canvas(canvasWidth, canvasHeigth);
		root.setCenter(canvas);

		HBox toolbar = new HBox();
		toolbar.setSpacing(10);
		toolbar.setPadding(new Insets(10, 10, 10, 10));
		root.setTop(toolbar);

		// A button to increase the score
		//And test that the score class is working
		Button increaseScore = new Button("Increase Score");
		toolbar.getChildren().add(increaseScore);

		//A button that gets the score and adds it to the high score list
		Button finalScore = new Button("Get Final Score");
		toolbar.getChildren().add(finalScore);

//		Score sc = new Score();
		HighScore highScore = new HighScore();

		Label label = new Label("Score: " + sc.getScore());
		toolbar.getChildren().add(label);

		increaseScore.setOnAction(e -> {
			System.out.println("here " + sc.getScore() );
			label.setText("Score: " + sc.getScore());
		});

		finalScore.setOnAction(e -> {
			label.setText("Score: " + sc.getFinalScore());

			highScore.writeToFile(sc.getFinalScore());
			highScore.displayHighScores();

		});


		Button startTickTimelineButton = new Button("Start Ticks");
		Button stopTickTimelineButton = new Button("Stop Ticks");
		toolbar.getChildren().addAll(startTickTimelineButton, stopTickTimelineButton);
		stopTickTimelineButton.setDisable(true);

		startTickTimelineButton.setOnAction(e -> {
			startTickTimelineButton.setDisable(true);
			tickTimeline.play();
			stopTickTimelineButton.setDisable(false);
		});

		stopTickTimelineButton.setOnAction(e -> {
			stopTickTimelineButton.setDisable(true);
			tickTimeline.stop();
			startTickTimelineButton.setDisable(false);
		});

		ImageView draggableImage = new ImageView();
		draggableImage.setImage(bombImage5);
		toolbar.getChildren().add(draggableImage);
		
		ImageView draggableImage2 = new ImageView();
		draggableImage2.setImage(deathRatImage);
		toolbar.getChildren().add(draggableImage2);
		
		ImageView draggableImage3 = new ImageView();
		draggableImage3.setImage(stopSignImage5);
		toolbar.getChildren().add(draggableImage3);
		
		ImageView draggableImage4 = new ImageView();
		draggableImage4.setImage(sterilisationImage);
		toolbar.getChildren().add(draggableImage4);
		
		ImageView draggableImage5 = new ImageView();
		draggableImage5.setImage(gasImage);
		toolbar.getChildren().add(draggableImage5);
		
		ImageView draggableImage6 = new ImageView();
		draggableImage6.setImage(femaleSexChangeImage);
		toolbar.getChildren().add(draggableImage6);

		ImageView draggableImage7 = new ImageView();
		draggableImage7.setImage(maleSexChangeImage);
		toolbar.getChildren().add(draggableImage7);

		ImageView draggableImage8 = new ImageView();
		draggableImage8.setImage(poisonImage);
		toolbar.getChildren().add(draggableImage8);
		
		draggableImage.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Dragboard db = draggableImage.startDragAndDrop(TransferMode.ANY);
				
				ClipboardContent content = new ClipboardContent();
				content.putString("Hello");
				db.setContent(content);

				event.consume();
			}
		});
		
		draggableImage2.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Dragboard db = draggableImage2.startDragAndDrop(TransferMode.ANY);

				ClipboardContent content = new ClipboardContent();
				content.putString("Hello");
				db.setContent(content);

				event.consume();
			}
		});
		
		draggableImage3.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Dragboard db = draggableImage3.startDragAndDrop(TransferMode.ANY);

				ClipboardContent content = new ClipboardContent();
				content.putString("Hello");
				db.setContent(content);

				event.consume();
			}
		});
		
		draggableImage4.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Dragboard db = draggableImage4.startDragAndDrop(TransferMode.ANY);

				ClipboardContent content = new ClipboardContent();
				content.putString("Hello");
				db.setContent(content);

				event.consume();
			}
		});
		
		draggableImage5.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Dragboard db = draggableImage5.startDragAndDrop(TransferMode.ANY);

				ClipboardContent content = new ClipboardContent();
				content.putString("Hello");
				db.setContent(content);

				event.consume();
			}
		});
		
		draggableImage6.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Dragboard db = draggableImage6.startDragAndDrop(TransferMode.ANY);

				ClipboardContent content = new ClipboardContent();
				content.putString("Hello");
				db.setContent(content);

				event.consume();
			}
		});
		
		draggableImage7.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Dragboard db = draggableImage7.startDragAndDrop(TransferMode.ANY);

				ClipboardContent content = new ClipboardContent();
				content.putString("Hello");
				db.setContent(content);

				event.consume();
			}
		});
		
		draggableImage8.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				Dragboard db = draggableImage8.startDragAndDrop(TransferMode.ANY);

				ClipboardContent content = new ClipboardContent();
				content.putString("Hello");
				db.setContent(content);

				event.consume();
			}
		});

		canvas.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				if (event.getGestureSource() == draggableImage) {
					event.acceptTransferModes(TransferMode.ANY);
					event.consume();
				}
				if (event.getGestureSource() == draggableImage2) {
					event.acceptTransferModes(TransferMode.ANY);
					event.consume();
				}
				if (event.getGestureSource() == draggableImage3) {
					event.acceptTransferModes(TransferMode.ANY);
					event.consume();
				}
				if (event.getGestureSource() == draggableImage4) {
					event.acceptTransferModes(TransferMode.ANY);
					event.consume();
				}
				if (event.getGestureSource() == draggableImage5) {
					event.acceptTransferModes(TransferMode.ANY);
					event.consume();
				}
				if (event.getGestureSource() == draggableImage6) {
					event.acceptTransferModes(TransferMode.ANY);
					event.consume();
				}
				if (event.getGestureSource() == draggableImage7) {
					event.acceptTransferModes(TransferMode.ANY);
					event.consume();
				}
				if (event.getGestureSource() == draggableImage8) {
					event.acceptTransferModes(TransferMode.ANY);
					event.consume();
				}
			}
		});
		
		canvas.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				canvasDragDroppedOccured(event);
				event.consume();
			}
		});

		return root;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
