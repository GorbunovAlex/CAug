package com.github.gorbunovalex.caug.toolWindow;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.content.ContentFactory;
import com.github.gorbunovalex.caug.services.MyProjectService;
import javax.swing.JButton;
import java.awt.*;

public class CAugToolWindowFactory implements ToolWindowFactory {

    private static final Logger LOGGER = Logger.getInstance(CAugToolWindowFactory.class);

    public CAugToolWindowFactory() {
        LOGGER.warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.");
    }

    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        CAugToolWindow myToolWindow = new CAugToolWindow(toolWindow);
        ContentFactory contentFactory = ContentFactory.getInstance();
        contentFactory.createContent(myToolWindow.getContent(), null, false);
        toolWindow.getContentManager().addContent(contentFactory.createContent(myToolWindow.getContent(), null, false));
    }

    @Override
    public boolean shouldBeAvailable(Project project) {
        return true;
    }

    public static class CAugToolWindow {

        private final MyProjectService service;

        public CAugToolWindow(ToolWindow toolWindow) {
            this.service = toolWindow.getProject().getService(MyProjectService.class);
        }

        public JBPanel getContent() {
            LayoutManager layout = new GridLayout(2, 1);
            JBPanel panel = new JBPanel(layout);

            JBBox inputBox = JBBox.createVerticalBox();
            JBTextField textField = new JBTextField();
            JButton button = new JButton("Send");
            inputBox.add(textField);
            inputBox.add(button);

            JBBox answerBox = new JBBox(1);
            answerBox.add(new JBLabel("Answer"));

            panel.add(new JBLabel("Chat with Ollama"));
            panel.add(inputBox);
            panel.add(answerBox);
            return panel;
        }
    }
}