package com.company;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.List;

public class Validator {
    private JavaSourceFromString sourceObject;
    private String className;
    private boolean compiled = false;

    public Validator(String className, String source) {
        this.className = className;
        sourceObject = new JavaSourceFromString(this.className, source);
    }

    public List<Diagnostic<? extends JavaFileObject>> compile() {
        // получаем компилятор, установленный в системе
        var compiler = ToolProvider.getSystemJavaCompiler();

        // компилируем
        var compilationUnits = Collections.singletonList(sourceObject);
        var diagnostics = new DiagnosticCollector<JavaFileObject>();
        compiled = compiler.getTask(null, null, diagnostics, null, null, compilationUnits).call();

        // возворащаем диагностику
        return diagnostics.getDiagnostics();
    }

    public TestResult testRun(String arg) {
        var result = new TestResult(false, "Failed to compile");
        if (compiled) {
            try {
                // загружаем класс
                var classLoader = URLClassLoader.newInstance(new URL[]{new File("").toURI().toURL()});
                var c = Class.forName(className, true, classLoader);
                // создаём объект класса
                var constructor = c.getConstructor();
                var instance = constructor.newInstance();
                // выполняем целевой метод
                c.getDeclaredMethod("receiveTick", String.class).invoke(instance, arg);
                result = new TestResult(true, "Success");
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException | RuntimeException | MalformedURLException | InstantiationException e) {
                var sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                result = new TestResult(false, sw.toString());
            }
        }
        return result;
    }
}
