package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

public enum Stream {

    CLOCKWISE{
        @Override public int giveFieldModifier()
        {
            return -1;
        }
        @Override public Stream giveOppositeStream()
        {
            return Stream.COUNTERCLOCKWISE;
        }
    },

    COUNTERCLOCKWISE{
        @Override public int giveFieldModifier()
        {
            return 1;
        }
        @Override public Stream giveOppositeStream()
        {
            return Stream.CLOCKWISE;
        }
    };

    public abstract int giveFieldModifier();
    public abstract Stream giveOppositeStream();

}
