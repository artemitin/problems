package test.test;

import java.util.List;

public class Chess {
    Player p1;
    Player p2;

}

class Figure {
    Player player;
    int type;
    int x;
    int y;
}

class Player {
    int n;
    List<Figure> figures;
}