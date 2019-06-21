package com.bmeproject.game.bmeProject.screens.battleScreen.battleController;

public enum Zone {

    RED{
        @Override
        public int getColorIndex()
        {
            return 0;
        }
    },
    GREEN
            {
                @Override
                public int getColorIndex() {
                    return 2;
                }
            },
    BLUE
            {
                @Override
                public int getColorIndex() {
                    return 4;
                }
            };

    private boolean activated;

    Zone()
    {
        activated = false;
    }

    public boolean isActivated(){
        return activated;
    }

    public void activate(){
        activated = true;
    }

    public void reset() {
        activated = false;
    }

    public abstract int getColorIndex();
}
