package edu.olezha.jsandbox.groovy;

import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import java.io.IOException;

public class UseGroovyScriptEngine {

    public static void main(String[] args) throws IOException, ResourceException, ScriptException {
        GroovyScriptEngine groovyScriptEngine = new GroovyScriptEngine(
                new String[] {System.getProperty("user.dir") + "/src/main/java/edu/olezha/jsandbox/groovy"});
        groovyScriptEngine.run("HelloGroovy.groovy", "");
    }
}
