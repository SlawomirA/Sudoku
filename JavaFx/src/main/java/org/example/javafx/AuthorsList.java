package org.example.javafx;

import java.util.ListResourceBundle;

public class AuthorsList extends ListResourceBundle {
    private static final Object[][] AUTHORS = {
            {"1", "Sławomir Andrzejczak"},
            {"2", "Piotr Skrobski"}
    };

    @Override
    protected Object[][] getContents() {
        return AUTHORS;
    }
}
