import edu.calpoly.spritely.ImageTile;
import edu.calpoly.spritely.Size;

import java.awt.*;
import java.io.IOException;

public class PyramidTile extends ImageTile {
    private Color color;
    private PyramidPiece pyramidPiece;

    public PyramidTile(Size var2, Color color, PyramidPiece pyramidPiece) throws IOException {
        super(pyramidPiece.getCard().getFile(), var2, pyramidPiece.getC());
        this.color = color;
        this.pyramidPiece = pyramidPiece;
    }

    @Override
    public void paint(Graphics2D graphics2D, Size size) {
        // fill the space with the correct color
        graphics2D.setColor(color);
        graphics2D.fillRect(0, 0, size.width, size.height);

        // add the image
        super.paint(graphics2D, size);
    }

    @Override
    public char getText() {
        return super.getText();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public PyramidPiece getPyramidPiece() {
        return pyramidPiece;
    }
}
