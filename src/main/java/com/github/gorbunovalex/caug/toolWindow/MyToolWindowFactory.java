package com.github.gorbunovalex.caug.toolWindow;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.content.ContentFactory;
import com.github.gorbunovalex.caug.MyBundle;
import com.github.gorbunovalex.caug.services.MyProjectService;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;

public class MyToolWindowFactory implements ToolWindowFactory {

    private static final Logger LOGGER = Logger.getInstance(MyToolWindowFactory.class);

    public MyToolWindowFactory() {
        LOGGER.warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.");
    }

    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        MyToolWindow myToolWindow = new MyToolWindow(toolWindow);
        ContentFactory contentFactory = ContentFactory.getInstance();
        contentFactory.createContent(myToolWindow.getContent(), null, false);
        toolWindow.getContentManager().addContent(contentFactory.createContent(myToolWindow.getContent(), null, false));
    }

    @Override
    public boolean shouldBeAvailable(Project project) {
        return true;
    }

    public static class MyToolWindow {

        private final MyProjectService service;

        public MyToolWindow(ToolWindow toolWindow) {
            this.service = toolWindow.getProject().getService(MyProjectService.class);
        }

        public JBPanel getContent() {
            JBPanel panel = new JBPanel();
            JBLabel label = new JBLabel("Hello");
            JButton button = new JButton("World");
            button.addActionListener(e -> label.setText("ITS ALIVE"));
            panel.add(label);
            panel.add(button);
            return panel;
        }
    }
}