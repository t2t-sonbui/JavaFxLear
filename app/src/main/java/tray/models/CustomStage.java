package tray.models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class CustomStage extends Stage {


    private final Rectangle2D screenBounds;
    private final double pannelWidth;
    private final double pannelHigh;

    public CustomStage(Pane ap, StageStyle style, Window stageOwner) {
        if (stageOwner != null) {
            initOwner(stageOwner);
        }
        initStyle(style);
        pannelWidth = ap.getPrefWidth();
        System.out.println("pannelWidth:"+pannelWidth);
        pannelHigh = ap.getPrefHeight();
        System.out.println("pannelHigh:"+pannelHigh);
        setSize(ap.getPrefWidth(), ap.getPrefHeight());

        screenBounds = Screen.getPrimary().getVisualBounds();

    }

    public CustomStage(Pane ap, StageStyle style) {
        this(ap, style, null);
    }

    public Location getBottomRight() {
        double x = screenBounds.getMinX() + screenBounds.getWidth() - pannelWidth - 2;
        double y = screenBounds.getMinY() + screenBounds.getHeight() - pannelHigh - 2;

        Location bottomRight = new Location(x, y);
        return bottomRight;
    }

    public Location getCenter() {

        double x = screenBounds.getMinX() + screenBounds.getWidth() + pannelWidth;
        double y = screenBounds.getMinY() + screenBounds.getHeight() + pannelHigh;

        Location center = new Location(x / 2, y / 2);
        return center;
    }


    public void setSize(double width, double height) {
        setWidth(width);
        setHeight(height);
    }

    public Location getOffScreenBounds() {
        Location loc = getBottomRight();

        return new Location(loc.getX() + this.getWidth(), loc.getY());
    }

    public void setLocation(Location loc) {
        setX(loc.getX());
        setY(loc.getY());
    }

    private SimpleDoubleProperty xLocationProperty = new SimpleDoubleProperty() {
        @Override
        public void set(double newValue) {
            setX(newValue);
        }

        @Override
        public double get() {
            return getX();
        }
    };

    public SimpleDoubleProperty xLocationProperty() {
        return xLocationProperty;
    }

    private SimpleDoubleProperty yLocationProperty = new SimpleDoubleProperty() {
        @Override
        public void set(double newValue) {
            setY(newValue);
        }

        @Override
        public double get() {
            return getY();
        }
    };

    public SimpleDoubleProperty yLocationProperty() {
        return yLocationProperty;
    }
}
