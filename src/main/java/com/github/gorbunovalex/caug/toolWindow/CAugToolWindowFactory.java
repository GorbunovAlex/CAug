package com.github.gorbunovalex.caug.toolWindow;

import com.github.gorbunovalex.caug.services.LlamaService;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.content.ContentFactory;

import javax.swing.JButton;
import java.awt.*;
import java.beans.EventHandler;
import java.io.IOException;

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

        private final LlamaService llama_service;

        public CAugToolWindow(ToolWindow toolWindow) {
            this.llama_service = toolWindow.getProject().getService(LlamaService.class);
        }

        public JBPanel getContent() {
            LayoutManager layout = new BorderLayout();
            JBPanel panel = new JBPanel(layout);

            JBBox inputBox = JBBox.createVerticalBox();
            JBTextField textField = new JBTextField();
            JButton button = new JButton("Send");

            button.addActionListener(e -> {
                String text = textField.getText();
                textField.setText("");
                System.out.println(text);
                try {
                    this.llama_service.main(text);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            inputBox.add(textField);
            inputBox.add(button);

            JBBox answerBox = new JBBox(1);
            answerBox.add(new JBLabel("Answer"));
            answerBox.setPreferredSize(new Dimension(200, 500));

            panel.add(new JBLabel("Chat with Ollama"), BorderLayout.PAGE_START);
            panel.add(inputBox, BorderLayout.CENTER);
            panel.add(answerBox, BorderLayout.PAGE_END);
            return panel;
        }
    }
}