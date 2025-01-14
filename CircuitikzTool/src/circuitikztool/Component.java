package circuitikztool;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import javax.swing.JOptionPane;

/**
 * Component is meant to be a data object for storing all possible component
 * objects in a single arrayList inside of the class CircuitMaker. For that
 * purpose this class handles path, node, and three terminal components and will
 * be adapted to handle all supported components.
 *
 * @author James
 */
public class Component {

    //path specific placement variables
    Point wireStart, wireEnd;

    //non path placement variables
    Point position;

    String latexParameters = "";          //stores the string which ultimately ends up in the LaTeX output, this is the variable a user modifies when they change the "Component String" field in the UI
    String Label = "";                    //User defined label that is displayed as "Component Label" in UI, meant for the user to help organize their schematic as it suits them

    int componentType;                    //this variable defines what "Type" of component we're using, please reference the constant vairables below for possible values. 
    private boolean pathComponent = true;

    //LaTeX doesn't like 3 terminal devices having the same name, we use this variable so that their labels are iterated everytime a new 3 terminal component is placed. 
    private static int TransistorCounter = 1;
    private static int OpAmpCounter = 1;
    private static int TransformerCount = 1;
    private static int BlockComponentCount = 1;

    //block component specific variables
    int BlockComponent_pinCount = 2;
    double BlockComponent_width = 1.45;

    //circuitikz requires us to give unique labels to components in order to connect nodes to them
    //for transistors and other multi-terminal devices we need to have a unique ID
    //the deviceID is only used in the LaTeX output
    private int deviceID;

    /*
        Since we have to handle as many components as possible with a single class we allow the class to define multiple different types of components
        in this way we are able to store everything in a single array list. 
    
        Important note: "Path Components" are components that have 2 terminals and go from point A to Point B (wires, resistors, capacitors, etc)
                        "non-path components" are components that have any number of terminals but only a single position variable (transistors, nodes, etc)
                        this distinction is important because there are two constructors one which is for path components and one that is not.
     */
    final static int PATH = 0;
    final static int RESISTOR = 1;
    final static int CAPACITOR = 2;
    final static int INDUCTOR = 3;
    final static int DIODE = 4;
    final static int VOLTAGE_SOURCE = 5;
    final static int CURRENT_SOURCE = 6;

    //non-path components
    final static int BLOCK_COMPONENT = 7;
    final static int GROUND_NODE = 8;
    final static int VCC_NODE = 9;
    final static int VSS_NODE = 10;
    final static int TRANSISTOR_NPN = 11;
    final static int TRANSISTOR_PNP = 12;
    final static int NMOS = 13;
    final static int PMOS = 14;
    final static int NIGBT = 15;
    final static int PIGBT = 16;
    final static int OPAMP_3TERMINAL = 17;
    final static int OPAMP_5TERMINAL = 18;
    final static int TRANSFORMER = 19;
    final static int TRANSFORMER_WITH_CORE = 20;

    //non-component commands used for Latex Component Builder
    final static int DELETE = 1000;
    final static int CANCEL = 1001;

    /**
     * Constructor for component as an option. In some instances (latex string
     * builder window) we need to return a component or a command. this
     * constructor allows us to pass a command as a component to logic higher
     * up.
     *
     * @param componentSelected
     */
    public Component(int componentSelected) {
        switch (componentSelected) {
            case DELETE:
                break;
            case CANCEL:
                break;
            default:
                throw new IllegalArgumentException("No NON-PATH component type exists for constant " + componentSelected);
        }
        pathComponent = false; //simple boolean for the class to know whether or not it's a pathing variable (there are other ways to test this but this is the easiest) 
        componentType = componentSelected; //set this object's componentType to the passed in value
    }

    /**
     * Constructor for NON-PATH components, requires only a position and a
     * selected component value. consult the constants at the top of the class
     * for valid input values to this function
     *
     * @param position position (in terms of the circuitikz placement) where the
     * component would be placed
     * @param componentSelected component selected, consult constant values
     * above for valid non-path components.
     */
    public Component(Point position, int componentSelected) {
        this.position = position;                                //pass the input parameter to the object

        /* Depending on the component selected we need to initalize the string parameter's such that the latex output is correct
        The values passed into latexParameters and Label are meant to be "template" values  
        when adding a non-path component it needs to be added ONLY to this constructor, the fact that this constructor throws an exception when
        a path component is input into it is very important to the functioning of the program.
         */
        switch (componentSelected) {
            case BLOCK_COMPONENT:
                deviceID = BlockComponentCount++;
                latexParameters = "node[dipchip, num pins=" + BlockComponent_pinCount + ", hide numbers, no topmark, external pins width=0](U" + BlockComponentCount + "){U" + BlockComponentCount + "};\n";

                //throw some nodes on the component block, we need something there in order for the 
                //put right nodes on the left side and left nodes on the right side (makes sense right?)
                for (int a = 1; a <= BlockComponent_pinCount; a++) {
                    if (a <= BlockComponent_pinCount / 2) {
                        latexParameters += "\\node [right, font=\\tiny] at (U" + BlockComponentCount + ".bpin " + a + ") {};";
                    } else {
                        latexParameters += "\\node [left, font=\\tiny] at (U" + BlockComponentCount + ".bpin " + a + ") {};";
                    }
                    latexParameters += "\n";
                }
                Label = "Generic Block";
                break;
            case TRANSISTOR_NPN:
                deviceID = TransistorCounter++;
                latexParameters = "node[npn](Q" + deviceID + "){Q" + deviceID + "}";
                Label = "NPN Transistor";
                break;
            case TRANSISTOR_PNP:
                deviceID = TransistorCounter++;
                latexParameters = "node[pnp](Q" + deviceID + "){Q" + deviceID + "}";
                Label = "PNP Transistor";
                break;
            case NMOS:
                deviceID = TransistorCounter++;
                latexParameters = "node[nmos](Q" + deviceID + "){Q" + deviceID + "}";
                Label = "N-MOS";
                break;
            case NIGBT:
                deviceID = TransistorCounter++;
                latexParameters = "node[nigbt](Q" + deviceID + "){Q" + deviceID + "}";
                Label = "N-IGBT";
                break;
            case PIGBT:
                deviceID = TransistorCounter++;
                latexParameters = "node[pigbt](Q" + deviceID + "){Q" + deviceID + "}";
                Label = "P-IGBT";
                break;
            case PMOS:
                deviceID = TransistorCounter++;
                latexParameters = "node[pmos](Q" + deviceID + "){Q" + deviceID + "}";
                Label = "P-MOS";
                break;
            case GROUND_NODE:
                latexParameters = "node[ground]{}";
                Label = "GND";
                break;
            case VCC_NODE:
                latexParameters = "node[vcc]{VCC}";
                Label = "VCC";
                break;
            case VSS_NODE:
                latexParameters = "node[vss]{VSS}";
                Label = "VSS";
                break;
            case OPAMP_3TERMINAL:
                //3 terminal and 5 terminal opamps are actually identical in terms of their 
                //original template string, however we have to treat them differently in the latex output
                //and in the drawing
                deviceID = OpAmpCounter++;
                latexParameters = "node[op amp,scale=2.04] (opamp" + deviceID + ") {}";
                Label = "3-Term Opamp";
                break;
            case OPAMP_5TERMINAL:
                deviceID = OpAmpCounter++;
                latexParameters = "node[op amp,scale=2.04] (opamp" + deviceID + ") {}";
                Label = "5-Term Opamp";
                break;
            case TRANSFORMER:
                deviceID = TransformerCount++;
                latexParameters = "node[transformer,scale=.952] (T" + deviceID + ") {}";
                Label = "Transformer";
                break;
            case TRANSFORMER_WITH_CORE:
                deviceID = TransformerCount++;
                latexParameters = "node[transformer core,scale=.952] (T" + deviceID + ") {}";
                Label = "Transformer w/ Core";
                break;

            default:
                //this exception is important in isPathComponent();
                //in the unlikely event that a path component some how used this constructor throw an error to alert the nearest code monkey
                throw new IllegalArgumentException("No NON-PATH component type exists for constant " + componentSelected);
        }
        pathComponent = false; //simple boolean for the class to know whether or not it's a pathing variable (there are other ways to test this but this is the easiest) 
        componentType = componentSelected; //set this object's componentType to the passed in value
    }

    /**
     * Constructor for PATH components including Wires, resistors, capacitors,
     * etc. Please consult the defined constants in CircuitMaker to determine
     * proper input values. Non-path components should not use this constructor,
     * the fact that this constructor throws an error when a non path component
     * is passed into it is important to the functioning of the program.
     *
     * this constructor also serves as the "ultimate list" of which components
     * are path components and which components are not path components, please
     * see isPathComponent()
     *
     * @param wireStart starting position of the path component (position is in
     * terms of circuitikz coordinates)
     * @param wireEnd ending position of the path component (position is in
     * terms of circuitikz coordinates)
     * @param componentSelected desired PATH component to be created, see
     * constants at the top of this class for acceptable values
     */
    public Component(Point wireStart, Point wireEnd, int componentSelected) {
        //pass start and end positions of the wire to the object
        this.wireStart = wireStart;
        this.wireEnd = wireEnd;

        /* Depending on the component selected we need to initalize the string parameter's such that the latex output is correct
        The values passed into latexParameters and Label are meant to be "template" values  
        when adding a path component it needs to be added ONLY using this constructor, the fact that this constructor throws an exception when
        a non-path component is input into it is very important to the functioning of the program.
         */
        switch (componentSelected) {
            case PATH:
                latexParameters = "to[short]";
                Label = "Wire";
                break;
            case RESISTOR:
                latexParameters = "to[generic,l=$R$]";
                Label = "R";
                break;
            case CAPACITOR:
                latexParameters = "to[C,l=$C$]";
                Label = "C";
                break;
            case INDUCTOR:
                latexParameters = "to[american inductor,l=$L$]";
                Label = "L";
                break;
            case DIODE:
                latexParameters = "to[D,l=$D$]";
                Label = "D";
                break;
            case VOLTAGE_SOURCE:
                latexParameters = "to[V,i_>=$I$, v<=$U$]";
                Label = "V";
                break;
            case CURRENT_SOURCE:
                latexParameters = "to[isource, i>=$I$]";
                Label = "I";
                break;
            default:
                //this exception is important in isPathComponent();
                //in the unlikely event that a non-path component some how used this constructor throw an error to alert the nearest code monkey
                throw new IllegalArgumentException("No PATH component type exists for constant " + componentSelected);
        }
        componentType = componentSelected; //pass the selected component value to the object
    }

    /** gets the identifier of a component as it would appear in the latex code
     *
     * @return component identifier used in latex code
     */
    public int getDeviceID() {
        return deviceID;
    }

    /** loads a component from a single line of XML
     *
     * @param xml XML line that contains the component data
     * @return loaded component object
     */
    public static Component getComponentFromXML(String xml) {
        if (getDataFromXMLTag(xml, "pathComponent").equals("true")) {
            Component ret = new Component(
                    new Point(Integer.parseInt(getDataFromXMLTag(xml, "start-x")), Integer.parseInt(getDataFromXMLTag(xml, "start-y"))),
                    new Point(Integer.parseInt(getDataFromXMLTag(xml, "end-x")), Integer.parseInt(getDataFromXMLTag(xml, "end-y"))),
                    Integer.parseInt(getDataFromXMLTag(xml, "type"))
            );
            ret.setLatexString(getDataFromXMLTag(xml, "latexParameters"));
            ret.setComponentLabel(getDataFromXMLTag(xml, "label"));
            return ret;
        } else {
            Component ret = new Component(
                    new Point(Integer.parseInt(getDataFromXMLTag(xml, "position-x")), Integer.parseInt(getDataFromXMLTag(xml, "position-y"))),
                    Integer.parseInt(getDataFromXMLTag(xml, "type"))
            );
            //special case for the block component, we need the pinCount in order to display it properly
            if (ret.componentType == BLOCK_COMPONENT) {
                ret.BlockComponent_pinCount = Integer.parseInt(getDataFromXMLTag(xml, "pinCount"));
            }
            ret.setLatexString(getDataFromXMLTag(xml, "latexParameters"));
            ret.setComponentLabel(getDataFromXMLTag(xml, "label"));
            return ret;
        }
    }

    /** pulls the data from a given XML line that is present within a given tag
     *
     * @param xml the single line of XML data to pull from
     * @param tag the tag within which is contained the desired data
     * @return the data from within the specified XML tag
     */
    public static String getDataFromXMLTag(String xml, String tag) {
        try {
            int startPos = xml.indexOf("<" + tag + ">") + tag.length() + 2;
            int endPos = xml.indexOf("</" + tag + ">");
            return xml.substring(startPos, endPos);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("tag \"" + tag + "\" not found");
            return "";
        }
    }

    /** Converts a component object into an XML string that can be loaded, used
     * for saving the components to files. 
     *
     * @return a string XML representation of the component 
     */
    public String toXML() {
        String ret = "<component>";
        if (pathComponent) {
            ret += "<pathComponent>true</pathComponent>";
            ret += "<start-x>" + wireStart.x + "</start-x>";
            ret += "<start-y>" + wireStart.y + "</start-y>";
            ret += "<end-x>" + wireEnd.x + "</end-x>";
            ret += "<end-y>" + wireEnd.y + "</end-y>";
        } else {
            ret += "<pathComponent>false</pathComponent>";
            ret += "<position-x>" + position.x + "</position-x>";
            ret += "<position-y>" + position.y + "</position-y>";
            
            //special case for the block components, we need to know the pinCount
            //in order to display them properly in the circuitmaker window.
            //this could be parsed directly out of the latex parameters but I decided
            //to just add an xml field, either way would work just as well
            if (componentType == BLOCK_COMPONENT) {
                ret += "<pinCount>" + BlockComponent_pinCount + "</pinCount>";
            }
        }
        ret += "<type>" + componentType + "</type>";
        ret += "<label>" + Label + "</label>";
        ret += "<latexParameters>" + latexParameters.replace("\n", "") + "</latexParameters>";
        ret += "</component>";
        return ret;
    }

    /** resets the count of each component present, really only used in the 
     * case of file loading when we want to start from scratch.
     *
     */
    public static void resetStatics() {
        TransistorCounter = 1;
        OpAmpCounter = 1;
        BlockComponentCount = 1;
    }

    /**
     * For components that are already initalized this function identifies a
     * component as path or not-path
     *
     * @return true if path component, false if not.
     */
    public boolean isPathComponent() {
        return pathComponent;
    }

    /**
     * Since the circuit maker class will need to know which indexes are pathing
     * components and which ones aren't we use this function to test a given
     * index to determine whether or not a component is a path component. This
     * relys on the constructor's being properly split between pathing and non
     * pathing components.
     *
     * @param componentIndex component index relating to one of the constants
     * defined at the top of CircuitMaker class
     * @return true if path component, false if not path component
     */
    public static boolean isPathComponent(int componentIndex) {
        try {
            Component c = new Component(new Point(0, 0), new Point(0, 0), componentIndex);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Paints the component to the schematic window, Since the schematic window
     * allows for variable grid size and for the user to move the schematic
     * around it requires the current grid size, the current offset (position of
     * the origin relative to 0,0) and the position whether or not the component
     * is selected (selected components are highlighted in a different color in
     * the schematicWindow)
     *
     * @param g Graphics object for the components to be draw onto
     * @param gridSize current gridSize of the graphics object, this variable
     * allows for the components to scale relatively
     * @param offset current offset of the grid which is changed when the user
     * pans around the schematic
     * @param selected whether or not this component is currently selected
     */
    public void paint(Graphics g, int gridSize, Point offset, boolean selected) {
        //if a component is selected we should set its color differently. 
        if (selected) {
            g.setColor(Preferences.selectedColor);
        } else {
            g.setColor(Preferences.componentColor);
        }

        /*
            Since this class handles a wide variety of components we have to paint 
        each component differently. In the case of path components we can more or 
        less draw the component the same and just display the label. however non-path 
        components vary a lot so we need to handle them individually 
         */
        if (pathComponent) {
            g.drawLine(
                    gridSize * (wireStart.x + offset.x),
                    gridSize * (wireStart.y + offset.y),
                    gridSize * (wireEnd.x + offset.x),
                    gridSize * (wireEnd.y + offset.y)
            );
        } else if (componentType == VCC_NODE) {
            drawVCCNode(g, gridSize, position.x + offset.x, position.y + offset.y);
        } else if (componentType == GROUND_NODE) {
            drawGNDNode(g, gridSize, position.x + offset.x, position.y + offset.y);
        } else if (componentType == VSS_NODE) {
            drawVSSNode(g, gridSize, position.x + offset.x, position.y + offset.y);
        } else if (componentType == OPAMP_3TERMINAL || componentType == OPAMP_5TERMINAL) {
            drawOpamp(g, gridSize, position.x + offset.x, position.y + offset.y, selected, componentType);
        } else if (componentType == TRANSFORMER || componentType == TRANSFORMER_WITH_CORE) {
            drawTransformer(g, gridSize, position.x + offset.x, position.y + offset.y, selected);
        } else if (componentType == BLOCK_COMPONENT) {
            drawBlockComponent(g, gridSize, position.x + offset.x, position.y + offset.y, BlockComponent_width, BlockComponent_pinCount, selected);
        } else {
            //if it's not any of those then it's a three terminal transistor so we just draw the transistor
            drawTransistor(g, gridSize, position.x + offset.x, position.y + offset.y, selected);
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
        Point labelPosition;

        /*            if the component we're drawing is a path component then we want to place the label right on the midpoint
        otherwise we can just place it at the position of the component.        
        since the user is allowed to zoom the position of the label needs to be calculated as a fraction of the
        gridSize
         */
        if (isPathComponent()) {
            labelPosition = new Point(
                    ((int) wireStart.getX() * gridSize + (int) wireEnd.getX() * gridSize) / 2,
                    ((int) wireStart.getY() * gridSize + (int) wireEnd.getY() * gridSize) / 2
            );
        } else if (componentType == VCC_NODE) {
            //vcc nodes have the label above the drawn component
            labelPosition = new Point(position.x * gridSize, position.y * gridSize - 2 * gridSize / 3);
        } else if (componentType == GROUND_NODE || componentType == VSS_NODE) {
            //VSS and GND nodes have the label displayed below the component
            labelPosition = new Point(position.x * gridSize, position.y * gridSize + 2 * gridSize / 3);
        } else {
            //if the component doesn't need any special label placement and isn't a pathing component
            //then we can just place the label directly on the position of the component
            labelPosition = new Point(position.x * gridSize, position.y * gridSize);
        }

        //calculate width of the string itself
        int stringWidth = g.getFontMetrics().stringWidth(Label);

        //padding of the label (how many pixels of black space around the text before the border) 
        int boxPadding = 3;

        //create bounding box for the string
        g.setColor(Preferences.backgroundColor);
        g.fillRect((int) labelPosition.getX() + offset.x * gridSize - stringWidth / 2 - boxPadding, (int) labelPosition.getY() + offset.y * gridSize + 2 - fontSize - boxPadding, stringWidth + boxPadding * 2, fontSize + boxPadding * 2);

        if (selected) {
            g.setColor(Preferences.selectedColor);
        } else {
            g.setColor(Preferences.componentColor);
        }

        //create white border around label so it pops a little better
        g.setColor(Preferences.componentColor);
        g.drawRect((int) labelPosition.getX() + offset.x * gridSize - stringWidth / 2 - boxPadding, (int) labelPosition.getY() + offset.y * gridSize + 2 - fontSize - boxPadding, stringWidth + boxPadding * 2, fontSize + boxPadding * 2);
        //draw label string
        g.drawString(Label, (int) labelPosition.getX() + offset.x * gridSize - stringWidth / 2, (int) labelPosition.getY() + offset.y * gridSize + 2);
    }

    /**
     *
     * @return component label string
     */
    public String getComponentLabel() {
        return Label;
    }

    /**
     * sets the component label
     *
     * @param text String the component label should be set to
     */
    public void setComponentLabel(String text) {
        Label = text;
    }

    /**
     * returns the latex parameters of the current component
     *
     * @return latex parameter string of the current component
     */
    public String getLatexString() {
        return latexParameters;
    }

    /**
     * FETs need some special treatment in the latex output, this function
     * returns true if the device is a FET device and false otherwise
     *
     * @return true if FET device
     */
    public boolean isFet() {
        return componentType == NMOS || componentType == PMOS;
    }

    /**
     * sets the latex parameters of the current component
     *
     * @param text latex parameter string to pass into the current component
     */
    public void setLatexString(String text) {
        latexParameters = text;
    }

    /**
     * returns the beginning coordinate of a path component, throws
     * IllegalStateException if component is not a path component
     *
     * @return starting coordinate (in circuitikz coordinates) of the current
     * path component
     */
    public Point getStart() {
        if (pathComponent) {
            return wireStart;
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * returns the end coordinate of a path component, throws
     * IllegalStateException if component is not a path component
     *
     * @return starting coordinate (in circuitikz coordinates) of the current
     * path component
     */
    public Point getEnd() {
        if (pathComponent) {
            return wireEnd;
        } else {
            throw new IllegalStateException();
        }
    }

    /** gets the position of a non-path component 
     *
     * @return point object representing the position of a component.
     */
    public Point getPosition() {
        if (pathComponent) {
            throw new IllegalStateException();
        } else {
            return position;
        }
    }

    /**
     * returns the component label string, including information about the
     * placement of the component for display in the UI.
     *
     * @return component label string with position information
     */
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

    /**
     * outputs the formatted LaTeX line representing this component, in special
     * cases this function may return multiple lines of LaTeX code
     *
     * @return Circuitikz code representing current component
     */
    public String getLatexLine() {
        String output = "";

        //path components are simple, just insert the label between the start and end position. 
        if (isPathComponent()) {
            output += "\\draw (";
            output += (int) wireStart.getX() + "," + (int) (-1) * (wireStart.getY()) + ") ";
            output += getLatexString() + " ";
            output += "(" + (int) getEnd().getX() + "," + (int) (-1) * getEnd().getY() + ");";
        } else {

            /*to deal with multi-terminal and other non-path components we have to consider special cases.             
              for the most part we can just print the position values and the latexString and be good, however in the cases of some components
            such as the BJT devices we need to make sure that their terminals are "broken out" to our standardized grid system so that everything plays nicely
            together in the final output, there are much better and more human-readable ways to do this in CircuiTikz however those are much more difficult to implement
            and for the time being this serves most of the functionality at the cost of outputing more code. 
             */
            if (componentType == BLOCK_COMPONENT) {
                output += "\\ctikzset{multipoles/thickness=3}\n"
                        + "\\ctikzset{multipoles/dipchip/width=" + BlockComponent_width + "}\n"
                        + "\\ctikzset{multipoles/dipchip/pin spacing={0.715}}";
                output += "\\draw (";
                if ((BlockComponent_pinCount / 2) % 2 == 0) {
                    output += (int) position.getX() + "," + (int) (-1) * (position.getY() + 0.5) + ") ";
                } else {
                    output += (int) position.getX() + "," + (int) (-1) * (position.getY()) + ") ";

                }
                output += getLatexString() + ";";

            } else {
                output += "\\draw (";
                output += (int) position.getX() + "," + (int) (-1) * (position.getY()) + ") ";
                output += getLatexString() + ";";

            }

            switch (componentType) {
                case BLOCK_COMPONENT:
                    //special handling for block components
                    break;
                case TRANSISTOR_NPN:
                    //breakout the BJT's terminals to fit with the current grid system
                    output += "\\draw (Q" + deviceID + ".C) to[short] (" + (int) position.getX() + "," + (int) (-1) * (position.getY() - 1) + ");\n";
                    output += "\\draw (Q" + deviceID + ".E) to[short] (" + (int) position.getX() + "," + (int) (-1) * (position.getY() + 1) + ");\n";
                    output += "\\draw (Q" + deviceID + ".B) to[short] (" + (int) (position.getX() - 1) + "," + (int) (-1) * (position.getY()) + ");";
                    break;
                case TRANSISTOR_PNP:
                    //breakout the BJT's terminals to fit with the current grid system
                    output += "\\draw (Q" + deviceID + ".E) to[short] (" + (int) position.getX() + "," + (int) (-1) * (position.getY() - 1) + ");\n";
                    output += "\\draw (Q" + deviceID + ".C) to[short] (" + (int) position.getX() + "," + (int) (-1) * (position.getY() + 1) + ");\n";
                    output += "\\draw (Q" + deviceID + ".B) to[short] (" + (int) (position.getX() - 1) + "," + (int) (-1) * (position.getY()) + ");";
                    break;
                case NMOS:
                    //breakout the fet's terminals to fit with the current grid system:
                    output += "\\draw (Q" + deviceID + ".D) to[short] (" + (int) position.getX() + "," + (int) (-1) * (position.getY() - 1) + ");\n";
                    output += "\\draw (Q" + deviceID + ".S) to[short] (" + (int) position.getX() + "," + (int) (-1) * (position.getY() + 1) + ");\n";
                    output += "\\draw (Q" + deviceID + ".G) to[short] (" + (int) (position.getX() - 1) + "," + (int) (-1) * (position.getY()) + ");";
                    break;
                case PMOS:
                    //breakout the fets's terminals to fit with the current grid system:
                    output += "\\draw (Q" + deviceID + ".S) to[short] (" + (int) position.getX() + "," + (int) (-1) * (position.getY() - 1) + ");\n";
                    output += "\\draw (Q" + deviceID + ".D) to[short] (" + (int) position.getX() + "," + (int) (-1) * (position.getY() + 1) + ");\n";
                    output += "\\draw (Q" + deviceID + ".G) to[short] (" + (int) (position.getX() - 1) + "," + (int) (-1) * (position.getY()) + ");";
                    break;
                case NIGBT:
                    //breakout the IGBT's terminals to fit with the current grid system:
                    output += "\\draw (Q" + deviceID + ".D) to[short] (" + (int) position.getX() + "," + (int) (-1) * (position.getY() - 1) + ");\n";
                    output += "\\draw (Q" + deviceID + ".S) to[short] (" + (int) position.getX() + "," + (int) (-1) * (position.getY() + 1) + ");\n";
                    output += "\\draw (Q" + deviceID + ".G) to[short] (" + (int) (position.getX() - 1) + "," + (int) (-1) * (position.getY()) + ");";
                    break;
                case PIGBT:
                    //breakout the IGBT's terminals to fit with the current grid system:
                    output += "\\draw (Q" + deviceID + ".S) to[short] (" + (int) position.getX() + "," + (int) (-1) * (position.getY() - 1) + ");\n";
                    output += "\\draw (Q" + deviceID + ".D) to[short] (" + (int) position.getX() + "," + (int) (-1) * (position.getY() + 1) + ");\n";
                    output += "\\draw (Q" + deviceID + ".G) to[short] (" + (int) (position.getX() - 1) + "," + (int) (-1) * (position.getY()) + ");";
                    break;

                case TRANSFORMER:
                    output += "\\draw (T" + deviceID + ".A1) to[short] (" + (int) (position.getX() - 1) + "," + (int) (-1) * (position.getY() - 1) + ");\n";
                    output += "\\draw (T" + deviceID + ".A2) to[short] (" + (int) (position.getX() - 1) + "," + (int) (-1) * (position.getY() + 1) + ");\n";

                    output += "\\draw (T" + deviceID + ".B1) to[short] (" + (int) (position.getX() + 1) + "," + (int) (-1) * (position.getY() - 1) + ");\n";
                    output += "\\draw (T" + deviceID + ".B2) to[short] (" + (int) (position.getX() + 1) + "," + (int) (-1) * (position.getY() + 1) + ");";

                    break;
                case TRANSFORMER_WITH_CORE:
                    output += "\\draw (T" + deviceID + ".A1) to[short] (" + (int) (position.getX() - 1) + "," + (int) (-1) * (position.getY() - 1) + ");\n";
                    output += "\\draw (T" + deviceID + ".A2) to[short] (" + (int) (position.getX() - 1) + "," + (int) (-1) * (position.getY() + 1) + ");\n";

                    output += "\\draw (T" + deviceID + ".B1) to[short] (" + (int) (position.getX() + 1) + "," + (int) (-1) * (position.getY() - 1) + ");\n";
                    output += "\\draw (T" + deviceID + ".B2) to[short] (" + (int) (position.getX() + 1) + "," + (int) (-1) * (position.getY() + 1) + ");";

                    break;
                case OPAMP_3TERMINAL:
                    //breakout the opamp's terminals to fit with the current grid system:
                    output += "\n\\draw (opamp" + deviceID + ".-) to[short] (" + (int) (position.getX() - 3) + "," + (int) (-1) * (position.getY() - 1) + ");\n";
                    output += "\\draw (opamp" + deviceID + ".+) to[short] (" + (int) (position.getX() - 3) + "," + (int) (-1) * (position.getY() + 1) + ");";
//                    output += "\\draw (opamp" + deviceID + ".out) to[short] (" + (int) (position.getX() +1 ) + "," + (int) (-1) * (position.getY()) + ");";
                    break;
                case OPAMP_5TERMINAL:
                    //breakout the opamp's terminals to fit with the current grid system:
                    output += "\n\\draw (opamp" + deviceID + ".-) to[short] (" + (int) (position.getX() - 3) + "," + (int) (-1) * (position.getY() - 1) + ");\n";
                    output += "\\draw (opamp" + deviceID + ".+) to[short] (" + (int) (position.getX() - 3) + "," + (int) (-1) * (position.getY() + 1) + ");";
//                    output += "\\draw (opamp" + deviceID + ".out) to[short] (" + (int) (position.getX()) + "," + (int) (-1) * (position.getY()) + ");";
                    break;
            }
        }
        output += "\n"; //an extra line break to be nice :)
        return output;
    }

    /**
     * draws the gndNode at an x and y position (in CircuiTikz coordinates) to
     * the schematic window
     *
     * @param g graphics object to be drawn onto
     * @param gridSize current size of the grid
     * @param xPos x position in circuitikz coordinates
     * @param yPos y position in circuitikz coordinates
     */
    public static void drawGNDNode(Graphics g, int gridSize, int xPos, int yPos) {
        g.drawLine(
                gridSize * xPos - gridSize / 4,
                gridSize * yPos,
                gridSize * xPos + gridSize / 4,
                gridSize * yPos
        );
        g.drawLine(
                gridSize * xPos - gridSize / 8,
                gridSize * yPos + gridSize / 8,
                gridSize * xPos + gridSize / 8,
                gridSize * yPos + gridSize / 8
        );
        g.drawLine(
                gridSize * xPos - gridSize / 16,
                gridSize * yPos + 2 * gridSize / 8,
                gridSize * xPos + gridSize / 16,
                gridSize * yPos + 2 * gridSize / 8
        );
    }

    /**
     * draws the vss node at an x and y position (in CircuiTikz coordinates) to
     * the schematic window
     *
     * @param g graphics object to be drawn onto
     * @param gridSize current size of the grid
     * @param xPos x position in circuitikz coordinates
     * @param yPos y position in circuitikz coordinates
     */
    public static void drawVSSNode(Graphics g, int gridSize, int xPos, int yPos) {
        g.drawLine(
                gridSize * xPos,
                gridSize * yPos,
                gridSize * xPos,
                gridSize * yPos + gridSize / 3
        );
        g.drawLine(
                gridSize * xPos,
                gridSize * yPos + gridSize / 3,
                gridSize * xPos - gridSize / 8,
                gridSize * yPos + gridSize / 5 - gridSize / 8
        );
        g.drawLine(
                gridSize * xPos,
                gridSize * yPos + gridSize / 3,
                gridSize * xPos + gridSize / 8,
                gridSize * yPos + gridSize / 5 - gridSize / 8
        );

    }

    /**
     * draws the vcc Node at an x and y position (in CircuiTikz coordinates) to
     * the schematic window
     *
     * @param g graphics object to be drawn onto
     * @param gridSize current size of the grid
     * @param xPos x position in circuitikz coordinates
     * @param yPos y position in circuitikz coordinates
     */
    public static void drawVCCNode(Graphics g, int gridSize, int xPos, int yPos) {
        g.drawLine(
                gridSize * xPos,
                gridSize * yPos,
                gridSize * xPos,
                gridSize * yPos - gridSize / 3
        );
        g.drawLine(
                gridSize * xPos,
                gridSize * yPos - gridSize / 3,
                gridSize * xPos - gridSize / 8,
                gridSize * yPos - gridSize / 5 + gridSize / 8
        );
        g.drawLine(
                gridSize * xPos,
                gridSize * yPos - gridSize / 3,
                gridSize * xPos + gridSize / 8,
                gridSize * yPos - gridSize / 5 + gridSize / 8
        );
    }

    /**
     * draws the transistor at an x and y position (in CircuiTikz coordinates)
     * to the schematic window, must
     *
     * @param g graphics object to be drawn onto
     * @param gridSize current size of the grid
     * @param xPos x position in circuitikz coordinates
     * @param yPos y position in circuitikz coordinates
     * @param selected boolean indicating whether or not the transistor should
     * be drawn as a selected component
     */
    public static void drawTransistor(Graphics g, int gridSize, int xPos, int yPos, boolean selected) {
        if (selected) {
            g.setColor(Preferences.selectedColor);
        } else {
            g.setColor(Preferences.componentColor);
        }
        g.drawLine(gridSize * xPos, gridSize * yPos, gridSize * xPos, gridSize * yPos - gridSize);
        g.drawLine(gridSize * xPos, gridSize * yPos, gridSize * xPos, gridSize * yPos + gridSize);
        g.drawLine(gridSize * xPos, gridSize * yPos, gridSize * xPos - gridSize, gridSize * yPos);
        g.setColor(Preferences.backgroundColor);
        g.fillOval(gridSize * xPos - gridSize / 3, gridSize * yPos - gridSize / 3, gridSize * 2 / 3, gridSize * 2 / 3);
        if (selected) {
            g.setColor(Preferences.selectedColor);
        } else {
            g.setColor(Preferences.componentColor);
        }
        g.drawOval(gridSize * xPos - gridSize / 3, gridSize * yPos - gridSize / 3, gridSize * 2 / 3, gridSize * 2 / 3);
    }

    
    /**
     * draws the transformer at an x and y position (in CircuiTikz coordinates)
     * to the schematic window, must
     *
     * @param g graphics object to be drawn onto
     * @param gridSize current size of the grid
     * @param xPos x position in circuitikz coordinates
     * @param yPos y position in circuitikz coordinates
     * @param selected boolean indicating whether or not the transistor should
     * be drawn as a selected component
     */
    public static void drawTransformer(Graphics g, int gridSize, int xPos, int yPos, boolean selected) {
        if (selected) {
            g.setColor(Preferences.selectedColor);
        } else {
            g.setColor(Preferences.componentColor);
        }

        //top and bottom horizontal lines on left side
        g.drawLine((xPos - 1) * gridSize, (yPos + 1) * gridSize, (int) ((xPos - .25) * gridSize), (yPos + 1) * gridSize);
        g.drawLine((xPos - 1) * gridSize, (yPos - 1) * gridSize, (int) ((xPos - .25) * gridSize), (yPos - 1) * gridSize);

        //vertical horizontal lines on left and right side
        g.drawLine((int) ((xPos + .25) * gridSize), (yPos + 1) * gridSize, (int) ((xPos + .25) * gridSize), (yPos - 1) * gridSize);
        g.drawLine((int) ((xPos - .25) * gridSize), (yPos + 1) * gridSize, (int) ((xPos - .25) * gridSize), (yPos - 1) * gridSize);

        //top and bottom horizontal lines on right side
        g.drawLine((xPos + 1) * gridSize, (yPos + 1) * gridSize, (int) ((xPos + .25) * gridSize), (yPos + 1) * gridSize);
        g.drawLine((xPos + 1) * gridSize, (yPos - 1) * gridSize, (int) ((xPos + .25) * gridSize), (yPos - 1) * gridSize);

        //draw some impedence-like symbols to differentiate the transformer a bit
        g.fillRect((int) ((xPos - .35) * gridSize), (int) ((yPos - .5) * gridSize), (int) (.2 * gridSize), gridSize);
        g.fillRect((int) ((xPos + .15) * gridSize), (int) ((yPos - .5) * gridSize), (int) (.2 * gridSize), gridSize);
    }

    /**
     * draws the transistor at an x and y position (in CircuiTikz coordinates)
     * to the schematic window, must
     *
     * @param g graphics object to be drawn onto
     * @param gridSize current size of the grid
     * @param xPos x position in circuitikz coordinates
     * @param yPos y position in circuitikz coordinates
     * @param selected boolean indicating whether or not the transistor should
     * be drawn as a selected component
     * @param component integer representing the component itself, since 5
     * terminal and 3 terminal opamps need to be drawn differently. (uses
     * constants defined at the top of Component class)
     */
    public static void drawOpamp(Graphics g, int gridSize, int xPos, int yPos, boolean selected, int component) {
        if (selected) {
            g.setColor(Preferences.selectedColor);
        } else {
            g.setColor(Preferences.componentColor);
        }

        Polygon opampBody = new Polygon();
        opampBody.addPoint(gridSize * (xPos + 2), gridSize * yPos);
        opampBody.addPoint((int) (gridSize * (xPos - 1)), (int) (gridSize * (yPos - 1.5)));
        opampBody.addPoint((int) (gridSize * (xPos - 1)), (int) (gridSize * (yPos + 1.5)));

        g.setColor(Preferences.backgroundColor);
        g.fillPolygon(opampBody);
        if (selected) {
            g.setColor(Preferences.selectedColor);
        } else {
            g.setColor(Preferences.componentColor);
        }
        g.drawPolygon(opampBody);

        //add terminals
        g.drawLine((int) (gridSize * (xPos - 3)), (int) (gridSize * (yPos - 1)), (int) (gridSize * (xPos - 1)), (int) (gridSize * (yPos - 1)));
        g.drawLine((int) (gridSize * (xPos - 3)), (int) (gridSize * (yPos + 1)), (int) (gridSize * (xPos - 1)), (int) (gridSize * (yPos + 1)));

        //finally add the inverting and non-inverting input indicators  
        //inverting indicator
        g.drawLine((int) (gridSize * (xPos - .4)), (int) (gridSize * (yPos - 1)), (int) (gridSize * (xPos - .8)), (int) (gridSize * (yPos - 1)));
        //non-inverting indicator
        g.drawLine((int) (gridSize * (xPos - .6)), (int) (gridSize * (yPos + .8)), (int) (gridSize * (xPos - .6)), (int) (gridSize * (yPos + 1.2)));
        g.drawLine((int) (gridSize * (xPos - .4)), (int) (gridSize * (yPos + 1)), (int) (gridSize * (xPos - .8)), (int) (gridSize * (yPos + 1)));

        //have to draw the power supply inputs for 5 terminal opamps 
        if (component == OPAMP_5TERMINAL) {
            g.fillOval(gridSize * (xPos) - gridSize / 8, gridSize * (yPos - 1) - gridSize / 8, gridSize / 4, gridSize / 4);
            g.fillOval(gridSize * (xPos) - gridSize / 8, gridSize * (yPos + 1) - gridSize / 8, gridSize / 4, gridSize / 4);
        }

    }

    /**Draws block component to the screen at a given x and y position. 
     *
     * @param g graphics object to be drawn onto
     * @param gridSize current size of the grid
     * @param xPos x position in circuitikz coordinates
     * @param yPos y position in circuitikz coordinates
     * @param width configured width of the block component (Currently fixed at 1.45)
     * @param pinCount number of pins the block component has 
     * @param selected whether the component is currently selected
     */
    public static void drawBlockComponent(Graphics g, int gridSize, int xPos, int yPos, double width, int pinCount, boolean selected) {

        //since we're essentially "fitting" circuitikz components onto our own
        //grid there sometimes needs to be some adjustment to the circuitmaker window
        //in this case when the components have an even number of pins the circuit representation
        //in the window needs to be adjusted differently from the odd
        double adj = 0.5;
        double boxadj = 0;
        if ((pinCount / 2) % 2 == 0) {
            adj = 1;
            boxadj = 0.5;
        }
        
        //basically draw a box 
        g.drawRect(
                gridSize * xPos - (int) (Math.ceil(width / 2) * gridSize),
                (int) (gridSize * (yPos - pinCount / 4.0 + boxadj)),
                (int) (Math.ceil(width) * gridSize),
                (int) (0.5 + pinCount / 2) * gridSize
        );

        //place the pin markers on either side of the block component, these won't 
        //be shown when compiled to latex, they're only a visual aid for the 
        //circuitmaker window
        for (int a = 0; a < pinCount / 2; a++) {
            g.drawRect(
                    gridSize * xPos - (int) (Math.ceil(width / 2) * gridSize) - 2,
                    (int) (gridSize * (yPos - pinCount / 4.0 + adj)) + a * gridSize - 2,
                    4,
                    4
            );
            g.drawRect(
                    gridSize * xPos + (int) (Math.ceil(width / 2) * gridSize) - 2,
                    (int) (gridSize * (yPos - pinCount / 4.0 + adj)) + a * gridSize - 2,
                    4,
                    4
            );
        }

    }

}
