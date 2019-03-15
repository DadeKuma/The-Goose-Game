package com.github.dadekuma.goosegame.processing;

import com.github.dadekuma.goosegame.processing.enums.EnumCommand;
import com.github.dadekuma.goosegame.processing.enums.EnumParameter;
import com.github.dadekuma.goosegame.processing.exception.ParameterNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class GooseCommand {
    private EnumCommand name;
    private Map<EnumParameter, String> parameters;

    public GooseCommand(EnumCommand name) {
        this(name, new HashMap<>());
    }

    public GooseCommand(EnumCommand name, EnumParameter parameterName, String parameterValue) {
        this(name, new HashMap<>());
        parameters.put(parameterName, parameterValue);
    }

    public GooseCommand(EnumCommand name, Map<EnumParameter, String> parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    public EnumCommand getName() {
        return name;
    }

    public Map<EnumParameter, String> getParameters() {
        return parameters;
    }

    public GooseCommand addParameter(EnumParameter name, String value){
        parameters.put(name, value);
        return this;
    }

    public String getValue(EnumParameter parameterName){
        if(!parameters.containsKey(parameterName))
            throw new ParameterNotFoundException();
        return parameters.get(parameterName);
    }


    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof GooseCommand))
            return false;
        GooseCommand c = (GooseCommand) obj;
        return c.getName().equals(name) &&
                c.getParameters().equals(parameters);
    }
}