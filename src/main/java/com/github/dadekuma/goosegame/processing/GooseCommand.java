package com.github.dadekuma.goosegame.processing;

public class GooseCommand {
    private EnumCommand name;
    private Object parameter;

    public GooseCommand(EnumCommand name) {
        this(name, null);
    }

    public GooseCommand(EnumCommand name, Object parameter) {
        this.name = name;
        this.parameter = parameter;
    }

    public EnumCommand getName() {
        return name;
    }

    public void setName(EnumCommand name) {
        this.name = name;
    }

    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof GooseCommand))
            return false;
        GooseCommand c = (GooseCommand) obj;
        return c.getName().equals(name) &&
                c.getParameter().equals(parameter);
    }
}
