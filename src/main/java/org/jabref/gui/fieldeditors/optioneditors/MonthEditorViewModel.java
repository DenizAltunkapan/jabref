package org.jabref.gui.fieldeditors.optioneditors;

import java.util.Arrays;
import java.util.Collection;

import javax.swing.undo.UndoManager;

import javafx.util.StringConverter;

import org.jabref.gui.autocompleter.SuggestionProvider;
import org.jabref.logic.integrity.FieldCheckers;
import org.jabref.model.database.BibDatabaseMode;
import org.jabref.model.entry.Month;
import org.jabref.model.entry.field.Field;
import org.jabref.model.strings.StringUtil;

public class MonthEditorViewModel extends OptionEditorViewModel<Month> {
    private final BibDatabaseMode databaseMode;

    public MonthEditorViewModel(Field field, SuggestionProvider<?> suggestionProvider, BibDatabaseMode databaseMode, FieldCheckers fieldCheckers, UndoManager undoManager) {
        super(field, suggestionProvider, fieldCheckers, undoManager);
        this.databaseMode = databaseMode;
    }

    @Override
    public StringConverter<Month> getStringConverter() {
        return new StringConverter<>() {
            @Override
            public String toString(Month object) {
                if (object == null) {
                    return null;
                } else {
                    if (databaseMode == BibDatabaseMode.BIBLATEX) {
                        return String.valueOf(object.getNumber());
                    } else {
                        return object.getJabRefFormat();
                    }
                }
            }

            @Override
            public Month fromString(String string) {
                if (StringUtil.isNotBlank(string)) {
                    return Month.parse(string).orElse(null);
                } else {
                    return null;
                }
            }
        };
    }

    @Override
    public Collection<Month> getItems() {
        return Arrays.asList(Month.values());
    }

    @Override
    public String convertToDisplayText(Month object) {
        return object.getFullName();
    }
}
