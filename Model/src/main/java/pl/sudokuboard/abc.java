package pl.sudokuboard;

abstract class Subclass  {
    boolean a;
    int b;

    Subclass() {
        int a;
        boolean test = true;

        if(test) {
            a=3;
        }
        else  {
            a=4;
        }
        System.out.println(a);
    }
}

