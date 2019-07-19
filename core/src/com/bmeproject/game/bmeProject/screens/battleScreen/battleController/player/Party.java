package com.bmeproject.game.bmeProject.screens.battleScreen.battleController.player;

import com.badlogic.gdx.math.Vector2;

public enum Party {
    // ===================================
    // ENUMERATIONS
    // ======================== ===========

    ALLY {
        @Override
        public float giveRotation() {
            return 0;
        }

        @Override
        public Vector2 giveSupplyVector() {
            return new Vector2(542, 41);
        }

        @Override
        public Vector2 giveGraveyardVector() {
            return new Vector2(230, 41);
        }

        @Override
        public Vector2 giveHandVector() {
            return new Vector2(280, 0);
        }
    },
    ENEMY {
        @Override
        public float giveRotation() {
            return 180;
        }

        @Override
        public Vector2 giveSupplyVector() {
            return new Vector2(230, 366);
        }

        @Override
        public Vector2 giveGraveyardVector() {
            return new Vector2(542, 366);
        }

        @Override
        public Vector2 giveHandVector() {
            return new Vector2(280, 406);
        }
    };

    // ===================================
    // METHODS
    // ===================================

    public abstract float giveRotation();

    public abstract Vector2 giveSupplyVector();

    public abstract Vector2 giveGraveyardVector();

    public abstract Vector2 giveHandVector();
}
