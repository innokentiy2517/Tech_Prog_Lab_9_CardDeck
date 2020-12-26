package sample;

import java.net.URL;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.util.Stack;

import static java.util.Collections.shuffle;

public class MainFormController implements Initializable {

    @FXML
    private AnchorPane Pane;
    @FXML
    private ListView<Label> Hearts;
    @FXML
    private ListView<Label> Spades;
    @FXML
    private ListView<Label> Diamonds;
    @FXML
    private ListView<Label> Clubs;
    @FXML
    private Label Card;

    private String[] Cards = new String[]
            {
                    "Two of Hearts", "Two of Spades", "Two of Diamonds", "Two of Clubs",
                    "Three of Hearts", "Three of Spades", "Three of Diamonds", "Three of Clubs",
                    "Four of Hearts", "Four of Spades", "Four of Diamonds", "Four of Clubs",
                    "Five of Hearts", "Five of Spades", "Five of Diamonds", "Five of Clubs",
                    "Six of Hearts", "Six of Spades", "Six of Diamonds", "Six of Clubs",
                    "Seven of Hearts", "Seven of Spades", "Seven of Diamonds", "Seven of Clubs",
                    "Eight of Hearts", "Eight of Spades", "Eight of Diamonds", "Eight of Clubs",
                    "Nine of Hearts", "Nine of Spades", "Nine of Diamonds", "Nine of Clubs",
                    "Ten of Hearts", "Ten of Spades", "Ten of Diamonds", "Ten of Clubs",
                    "Jack of Hearts", "Jack of Spades", "Jack of Diamonds", "Jack of Clubs",
                    "Queen of Hearts", "Queen of Spades", "Queen of Diamonds", "Queen of Clubs",
                    "King of Hearts", "King of Spades", "King of Diamonds", "King of Clubs",
                    "Ace of Hearts", "Ace of Spades", "Ace of Diamonds", "Ace of Clubs"
            };


    Stack <String> cardsDeck = new Stack<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        shuffle(Arrays.asList(Cards));
        for (int i = 0; i < Cards.length; i++){
            cardsDeck.push(Cards[i]);
        }

        Card.setText(cardsDeck.pop());

    }



    public void onDragDetected(MouseEvent mouseEvent) {

        Node sourceNode = (Node) mouseEvent.getSource();
        Dragboard db = sourceNode.startDragAndDrop(TransferMode.ANY);

        ClipboardContent content = new ClipboardContent();
        content.putString("");
        db.setContent(content);

        mouseEvent.consume();
    }

    public void onListViewDragOver(DragEvent dragEvent)
    {
        if (Card.getText().contains("Hearts") && dragEvent.getSource() == Hearts ||
                Card.getText().contains("Spades") && dragEvent.getSource() == Spades ||
                Card.getText().contains("Diamonds") && dragEvent.getSource() == Diamonds ||
                Card.getText().contains("Clubs") && dragEvent.getSource() == Clubs) {
            dragEvent.acceptTransferModes(TransferMode.ANY);
        }
    }

    public void onListViewDragDropped(DragEvent dragEvent) {
        ListView<Label> targetListView = (ListView) dragEvent.getGestureTarget();

        javafx.scene.control.Label sourceLabel = (Label) dragEvent.getGestureSource();
        targetListView.getItems().add(sourceLabel);
        Pane.getChildren().remove(sourceLabel);
        try{
            Card = new Label(cardsDeck.pop());
            Card.setId("Card");
            Card.setFont(Font.font("Georgia", 24));
            Card.setStyle("-fx-border-color: BLACK;");
            Card.setLayoutX(25);
            Card.setLayoutY(14);
            Card.setPadding(new Insets(10, 0, 10, 0));
            Card.setPrefSize(240, 54);
            Card.setOnDragDetected(this::onDragDetected);
            Card.setAlignment(Pos.CENTER);
            Hearts.setStyle("-fx-background-color:  LightPink; -fx-border-color: BLACK;");
            Diamonds.setStyle("-fx-background-color:  LightPink; -fx-border-color: BLACK;");
            Clubs.setStyle("-fx-background-color:   LightGray; -fx-border-color: BLACK;");
            Spades.setStyle("-fx-background-color:   LightGray; -fx-border-color: BLACK;");
            Pane.getChildren().add(Card);
        } catch (EmptyStackException e) {
            Card = new Label("Deck is empty!");
            Card.setId("End of a deck");
            Card.setFont(Font.font("Georgia", 24));
            Card.setStyle("-fx-border-color: BLACK;");
            Card.setLayoutX(25);
            Card.setLayoutY(14);
            Card.setPadding(new Insets(10, 0, 10, 0));
            Card.setPrefSize(240, 54);
            Card.setAlignment(Pos.CENTER);
            Hearts.setStyle("-fx-background-color:  LightPink; -fx-border-color: BLACK;");
            Diamonds.setStyle("-fx-background-color:  LightPink; -fx-border-color: BLACK;");
            Clubs.setStyle("-fx-background-color:   LightGray; -fx-border-color: BLACK;");
            Spades.setStyle("-fx-background-color:   LightGray; -fx-border-color: BLACK;");
            Pane.getChildren().add(Card);
        }

    }
}

