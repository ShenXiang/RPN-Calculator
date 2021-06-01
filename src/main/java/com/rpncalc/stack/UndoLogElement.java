package com.rpncalc.stack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UndoLogElement {

    private Object value;

    private UndoLogElementType type;
}
