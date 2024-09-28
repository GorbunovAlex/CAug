package com.github.gorbunovalex.caug.services;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.github.gorbunovalex.caug.MyBundle;
import org.jetbrains.annotations.NotNull;

@Service(Service.Level.PROJECT)
public final class MyProjectService {
    private final Project project;

    public MyProjectService(@NotNull Project project) {
        this.project = project;
        Logger.getInstance(MyProjectService.class).info("NAAAAAAMEEEEE:" + project.getName());
        Logger.getInstance(MyProjectService.class).warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.");
    }

    public int getRandomNumber() {
        return (int) (Math.random() * 100) + 1;
    }
}