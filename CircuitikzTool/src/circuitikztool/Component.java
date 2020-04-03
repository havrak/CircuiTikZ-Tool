package circuitikztool;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Component {

    //path specific placement variables
    Point wireStart, wireEnd;

    //non path placement variables
    Point position;

    BufferedImage Icon;
    String Text = "";
    String Label = "";

    int componentType;
    private boolean pathComponent = true;

    //path components
    final static int PATH = 0;
    final static int RESISTOR = 1;
    final static int CAPACITOR = 2;
    final static int INDUCTOR = 3;
    final static int DIODE = 4;
    final static int VOLTAGE_SOURCE = 5;
    final static int CURRENT_SOURCE = 6;
    final static int GROUND_NODE = 7;
    final static int VCC_NODE = 8;

    //non-path components
    final static int TRANSISTOR = 9;

    private static int nonPathCount = 1;

    public Component(Point position, int componentSelected) {
        this.position = position;
        switch (componentSelected) {
            case TRANSISTOR:
                Text = "node[npn](Q" + nonPathCount++ + "){}";
                Label = "Transistor";
                break;
            default:
                throw new IllegalArgumentException("No NON-PATH component type exists for constant " + componentSelected);
        }
        pathComponent = false;
    }

    public Component(Point wireStart, Point wireEnd, int componentSelected) {
        this.wireStart = wireStart;
        this.wireEnd = wireEnd;
        switch (componentSelected) {
            case PATH:
                Text = "to[short]";
                Label = "Wire";
                break;
            case RESISTOR:
                Text = "to[R,l=$R$]";
                Label = "R";
                break;
            case CAPACITOR:
                Text = "to[C,l=$C$]";
                Label = "C";
                break;
            case INDUCTOR:
                Text = "to[L,l=$L$]";
                Label = "L";
                break;
            case DIODE:
                Text = "to[D,l=$D$]";
                Label = "D";
                break;
            case VOLTAGE_SOURCE:
                Text = "to[V,l=$V$]";
                Label = "V";
                break;
            case CURRENT_SOURCE:
                Text = "to[isource,l=$I$]";
                Label = "I";
                break;
            case GROUND_NODE:
                Text = "node[ground]{}";
                Label = "GND";
                break;
            case VCC_NODE:
                Text = "node[vcc]{}";
                Label = "VCC";
                break;
            default:
                throw new IllegalArgumentException("No PATH component type exists for constant " + componentSelected);
        }
        componentType = componentSelected;
    }

    public boolean isPathComponent() {
        return pathComponent;
    }

    /*Since the circuit maker class will need to know which indexes are pathing components and which ones arent
    we use this function to test a given index to determine whether or not a component is a path component. 
    This relys on the constructor's being properly split between pathing and non pathing components*/
    public static boolean isPathComponent(int componentIndex) {
        try {
            Component c = new Component(new Point(0, 0), new Point(0, 0), componentIndex);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public void paint(Graphics g, int gridSize, Point offset, boolean selected, int gridX, int gridY) {
        if (selected) {
            g.setColor(Color.blue);
        } else {
            g.setColor(Color.white);
        }
        if (pathComponent) {
            g.drawLine(
                    gridSize * (wireStart.x + offset.x),
                    gridSize * (wireStart.y + offset.y),
                    gridSize * (wireEnd.x + offset.x),
                    gridSize * (wireEnd.y + offset.y)
            );
        } else {
            g.drawLine(gridSize * (position.x + offset.x), gridSize * (position.y + offset.y), gridSize * (position.x + offset.x), gridSize * (position.y + offset.y) - gridSize);
            g.drawLine(gridSize * (position.x + offset.x), gridSize * (position.y + offset.y), gridSize * (position.x + offset.x), gridSize * (position.y + offset.y) + gridSize);
            g.drawLine(gridSize * (position.x + offset.x), gridSize * (position.y + offset.y), gridSize * (position.x + offset.x) - gridSize, gridSize * (position.y + offset.y));
            g.setColor(Color.BLACK);
            g.fillOval(gridSize * (position.x + offset.x) - gridSize / 3, gridSize * (position.y + offset.y) - gridSize / 3, gridSize * 2 / 3, gridSize * 2 / 3);
            if (selected) {
                g.setColor(Color.blue);
            } else {
                g.setColor(Color.white);
            }
            g.drawOval(gridSize * (position.x + offset.x) - gridSize / 3, gridSize * (position.y + offset.y) - gridSize / 3, gridSize * 2 / 3, gridSize * 2 / 3);
        }

        /*
                    this section of code implements the "Draw label to wire" functionality of the circuitmaker, 
                this way the user can always see what the label of any component is while they're working on the 
                schematic. 
                we first calculate the midpoint which is where we place the label. we then fill the background of the string black for visibility 
                then we draw the string
         */
        int fontSize = 10;
        g.setFont(new Font("Dialog", Font.PLAIN, fontSize));
        Point mid;

        /*            if the component we're drawing is a path component then we want to place the label right on the midpoint
        otherwise we can just place it at the position of the component.        */
        if (isPathComponent()) {
            mid = new Point(
                    ((int) wireStart.getX() * gridSize + (int) wireEnd.getX() * gridSize) / 2,
                    ((int) wireStart.getY() * gridSize + (int) wireEnd.getY() * gridSize) / 2
            );
        } else {
            mid = new Point(position.x * gridSize, position.y * gridSize);
        }

        //calculate width of the string itself
        int stringWidth = g.getFontMetrics().stringWidth(Label);

        int boxPadding = 3;

        //create bounding box for the string
        g.setColor(Color.BLACK);
        g.fillRect((int) mid.getX() + gridX - stringWidth / 2 - boxPadding, (int) mid.getY() + gridY + 2 - fontSize - boxPadding, stringWidth + boxPadding * 2, fontSize + boxPadding * 2);

        if (selected) {
            g.setColor(Color.blue);
        } else {
            g.setColor(Color.white);
        }

        g.setColor(Color.WHITE);
        g.drawRect((int) mid.getX() + gridX - stringWidth / 2 - boxPadding, (int) mid.getY() + gridY + 2 - fontSize - boxPadding, stringWidth + boxPadding * 2, fontSize + boxPadding * 2);
        //draw label string
        g.drawString(Label, (int) mid.getX() + gridX - stringWidth / 2, (int) mid.getY() + gridY + 2);
    }

    public String getComponentLabel() {
        return Label;
    }

    public void setComponentLabel(String text) {
        Label = text;
    }

    public String getComponentString() {
        return Text;
    }

    public void setComponentString(String text) {
        Text = text;
    }

    public Point getStart() {
        return wireStart;
    }

    public Point getEnd() {
        return wireEnd;
    }

    public String getComponentLabelString() {
        if (isPathComponent()) {
            String retString = "";
            retString += Label + " ";
            retString += "[" + wireStart.x + "," + wireStart.y + "] to [" + wireEnd.x + "," + wireEnd.y + "]";
            return retString;
        } else {
            String retString = "";
            retString += Label + " ";
            retString += "[" + position.x + "," + position.y + "] ";
            return retString;
        }
    }

    String getLatexLine() {
        String output = "";
        if (isPathComponent()) {
            output += "\\draw (";
            output += (int) wireStart.getX() + "," + (int) (-1) * (wireStart.getY()) + ") ";
            output += getComponentString() + " ";
            output += "(" + (int) getEnd().getX() + "," + (int) (-1) * getEnd().getY() + ");";
        } else {
            output += "\\draw (";
            output += (int) position.getX() + "," + (int) (-1) * (position.getY()) + ") ";
            output += getComponentString() + ";";
        }
        output += "\n";
        return output;
    }

}
