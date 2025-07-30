import java.awt.geom.Ellipse2D;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class PyramidMoveCommand implements InterfaceCommand {
    private PyramidModel pyramidModel;
    private PyramidMove matchMove;
    private Deque<PyramidMove> matchMoves = new ArrayDeque<>();

    public PyramidMoveCommand(PyramidModel pyramidModel) {
        this.pyramidModel = pyramidModel;
    }

    @Override
    public void execute() {
        // ArrayList<CchMarble> starList = new ArrayList<>(pyramidModel.getStarList());
        //
        // int startX = matchMove.getStart().getX();
        // int startY = matchMove.getStart().getY();
        // int endX = matchMove.getEnd().getX();
        // int endY = matchMove.getEnd().getY();
        //
        // CchMarble startMarble = pyramidModel.marbleAtMyCoord(startX, startY);
        // Ellipse2D startE = startMarble.getEllipse();
        //
        // CchMarble endMarble = pyramidModel.marbleAtMyCoord(endX, endY);
        // Ellipse2D endE = endMarble.getEllipse();
        //
        // CchMarble replacementMarble = new CchMarble(endMarble);
        // replacementMarble.setCchPlayer(pyramidModel.getCurrentPlayer());
        // CchMarble blankMarble = new CchMarble(startMarble);
        // blankMarble.setCchPlayer(CchPlayer.BLANK);
        //
        // replacementMarble.setEllipse(endE);
        // blankMarble.setEllipse(startE);
        //
        // starList.remove(startMarble);
        // starList.remove(endMarble);
        // starList.add(replacementMarble);
        // starList.add(blankMarble);
        //
        // pyramidModel.setStarList(starList);
        // matchMoves.push(new MatchMove(matchMove));
    }

    @Override
    public void undo() {
        // ArrayList<CchMarble> starList = new ArrayList<>(pyramidModel.getStarList());
        //
        // // pollFirst return null if the deque is empty
        // MatchMove undoMove = matchMoves.pollFirst();
        // if(undoMove != null) {
        // int startX = undoMove.getStart().getX();
        // int startY = undoMove.getStart().getY();
        // int endX = undoMove.getEnd().getX();
        // int endY = undoMove.getEnd().getY();
        //
        // CchMarble startMarble = pyramidModel.marbleAtMyCoord(endX, endY);
        // Ellipse2D startE = startMarble.getEllipse();
        //
        // CchMarble endMarble = pyramidModel.marbleAtMyCoord(startX, startY);
        // Ellipse2D endE = endMarble.getEllipse();
        //
        // CchMarble replacementMarble = new CchMarble(endMarble);
        // replacementMarble.setCchPlayer(pyramidModel.getPreviousPlayer());
        // CchMarble blankMarble = new CchMarble(startMarble);
        // blankMarble.setCchPlayer(CchPlayer.BLANK);
        //
        // replacementMarble.setEllipse(endE);
        // blankMarble.setEllipse(startE);
        //
        // starList.remove(startMarble);
        // starList.remove(endMarble);
        // starList.add(replacementMarble);
        // starList.add(blankMarble);
        //
        // pyramidModel.setStarList(starList);
        // // set current player to the previous player
        // pyramidModel.setCurrentPlayer(pyramidModel.getPreviousPlayer());
        // pyramidModel.changeOccurred();
        // }
    }

    public void pushEmptyMove() {
        // // can just push a random, senseless move of -1s
        // // only a place holder so when we undo, the list still has a move for the turn that got
        // skipped
        // matchMoves.push(new MatchMove(new MatchPoint(-1, -1), new MatchPoint(-1, -1)));
        // pyramidModel.changeOccurred();
    }

    public void setMatchMove(PyramidMove matchMove) {
        this.matchMove = matchMove;
    }
}
